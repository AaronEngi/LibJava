package com.github.aaronengi.unit

import java.util.*

object ImplementHolder {
    private val map: MutableMap<Class<*>, Any> = HashMap()

    fun put(c: Class<*>, o: Any) {
        map[c] = o
    }

    operator fun <T> get(c: Class<T>): T? {
        return map[c] as T?
    }
}