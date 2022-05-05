package org.knism.gui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.knism.KTrack

@Composable
fun displaySpecificControls(tracks: SnapshotStateList<KTrack>) {
    Box(Modifier.fillMaxSize().padding(64.dp, 12.dp)) {
        val s = rememberScrollState(0)
        Box(Modifier.fillMaxSize().verticalScroll(s).padding(16.dp, 16.dp)) {
            Column {
                for (i in tracks) {
                    kTrackControl(i)
                }
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(), adapter = rememberScrollbarAdapter(s)
        )
    }
}