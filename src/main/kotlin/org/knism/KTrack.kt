package org.knism

import java.io.File
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class KTrack(val file: File) {

    var cFrame = 0
    val name: String
        get() = file.name

    private val callbacks = mutableListOf<(KProperty<*>, Float, Float) -> Unit>()

    fun registerListener(callback: (KProperty<*>, Float, Float) -> Unit) = callbacks.add(callback)
    fun setVol(it: Float) {
        volume = it
    }

    private var volume: Float by Delegates.observable(-40f) { property, oldValue, newValue ->
        callbacks.forEach { it(property, oldValue, newValue) }
    }
}