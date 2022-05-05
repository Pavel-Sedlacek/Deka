package org.knism.gui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.io.File

@Composable
fun fileSelector(onFileUpload: (File) -> Unit) {

    val dialog = remember { mutableStateOf(false) }

    Button(
        content = { Text("add custom") },
        onClick = { dialog.value = !dialog.value }
    )

    showFileDialog(dialog, onFileUpload)
}

@Composable
private fun showFileDialog(show: MutableState<Boolean>, onFileUpload: (File) -> Unit) {
    if (show.value) {
        Dialog(onCloseRequest = { show.value = false }) {
            filePicker { show.value = false; onFileUpload(it) }
        }
    }
}

@Composable
fun filePicker(onFileUpload: (File) -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
    ) {

        val f = remember { Node("root", "/", 0) }
        val s = rememberScrollState(0)

        Column(Modifier.verticalScroll(s)) {
            f.draw(
                onSelect = onFileUpload
            )
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(), adapter = rememberScrollbarAdapter(s)
        )

    }
}

data class Node(
    val name: String,
    val absolutePath: String,
    val level: Int,
    var opened: Boolean = false,
    val c: MutableList<Node> = mutableListOf()
) {
    @Composable
    fun draw(onSelect: (File) -> Unit) {

        val children = remember { mutableStateListOf<Node>() }

        ClickableText(
            text = AnnotatedString(name),
            modifier = Modifier.padding(start = (16 * level).dp, top = 4.dp),
            onClick = {
                if (opened) close().also { children.clear() }
                else children.addAll(open(onSelect))
            }
        )
        children.forEach {
            it.draw(onSelect)
            Box(Modifier.height(0.5.dp).fillMaxWidth().background(Color(0, 0, 0)))
        }
    }

    private fun get(): Content = Content(level, name)

    private fun open(onSelect: (File) -> Unit): List<Node> {
        val p = File(absolutePath)
        if (p.isFile) {
            onSelect(File(absolutePath))
            return listOf()
        }
        this.opened = true
        c.addAll(p.listFiles().map { Node(it.name, it.absolutePath, level + 1, false) }.filter { !it.name.contains(".") || it.name.substringAfter(".") == "wav" }.sortedBy { it.name })
        return c
    }

    private fun close() {
        this.opened = false
        this.c.clear()
    }
}

data class Content(val indentLevel: Int, val name: String)