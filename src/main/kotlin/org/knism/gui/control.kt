package org.knism.gui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.knism.AudioPlayer

@Composable
fun playerControl(audioPlayer: AudioPlayer) {
    Box(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(16.dp, 4.dp)) {
            Button(onClick = { audioPlayer.resume() }, content = { Text(">") })
            Button(onClick = { audioPlayer.pause() }, content = { Text("||") })
        }
    }
}