package org.knism

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.WindowPosition.PlatformDefault.x
import org.knism.gui.App
import java.io.File

fun main() {

    val audioPlayer = AudioPlayer()
    audioPlayer.prepare()

    val tracks = File("src/main/resources/").listFiles().map { KTrack(it) }

    tracks.forEach {audioPlayer.addTrack(it) }

    val y = App()

    y.launch(audioPlayer, tracks)

}
