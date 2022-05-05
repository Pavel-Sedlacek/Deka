package org.knism.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skiko.currentSystemTheme
import org.knism.AudioPlayer
import org.knism.KTrack

class App {
    fun launch(audioPlayer: AudioPlayer, baseTracks: List<KTrack>) = application {

        val tracks = remember { baseTracks.toMutableStateList() }
        CustomTheme(false) {
            Window(title = "Deka", undecorated = false, onCloseRequest = ::exitApplication) {
                Box(Modifier.fillMaxSize()) {

                    Column {

                        navbar()

                        playerControl(audioPlayer)

                        fileSelector { f ->
                            val track = KTrack(f)
                            audioPlayer.addTrack(track)
                            tracks.add(track)
                        }

                        displaySpecificControls(tracks)
                    }
                }
            }
        }
    }
}