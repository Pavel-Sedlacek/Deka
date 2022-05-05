package org.knism

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.sound.sampled.AudioSystem.getAudioInputStream
import javax.sound.sampled.AudioSystem.getLine
import javax.sound.sampled.Clip
import javax.sound.sampled.DataLine
import javax.sound.sampled.FloatControl
import kotlin.concurrent.thread


class AudioPlayer {

    private var running = false

    private val tracks = mutableListOf<Pair<KTrack, Clip>>()

    fun addTrack(track: KTrack) {

        val audioStream = getAudioInputStream(track.file)
        val format = audioStream.format
        val info = DataLine.Info(Clip::class.java, format)
        val audioClip = getLine(info) as Clip
        audioClip.open(audioStream)
        (audioClip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl).value = -40f
        track.registerListener { _, new, _ ->
            (audioClip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl).value = new
        }

        tracks.add(
            track to audioClip
        )
    }

    fun pause() {
        running = false
        tracks.forEach { it.first.cFrame = it.second.framePosition; it.second.stop() }
    }

    fun resume() {
        running = true
        tracks.forEach { it.second.framePosition = it.first.cFrame }
    }

    fun prepare() {
        thread {
            runBlocking {
                while (true) {
                    if (running) {
                        tracks.forEach {
                            it.second.start()
                            it.second.loop(Clip.LOOP_CONTINUOUSLY)
                        }
                        while (running) {
                            delay(200)
                        }
                    } else {
                        delay(200)
                    }
                }
            }
        }
    }
}