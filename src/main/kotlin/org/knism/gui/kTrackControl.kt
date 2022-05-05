package org.knism.gui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.knism.KTrack

@Composable
fun kTrackControl(track: KTrack) {

    val x = remember { mutableStateOf(-40f) }

    track.registerListener { _, new, _ ->
        x.value = new
    }

    Column {
        Text(track.name)
        Slider(
            value = x.value,
            onValueChange = {
                x.value = it
                track.setVol(it)
            },
            valueRange = -40f..6.0206f,
        )
    }
}