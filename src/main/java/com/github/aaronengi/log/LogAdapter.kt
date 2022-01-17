package com.github.aaronengi.log

import tyrael.DebugConfig
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object LogAdapter {
    private val sDebugConfig = DebugConfig.getInstance()
    private val sDateFormat: DateFormat = SimpleDateFormat("yyyy.MM.dd. HH:mm:ss SSS")

    /**
     * 记录一个绝对不可能的事。
     * 如果是可能的事，应该被处理；或者用w打印。
     */
    fun e(tag: String, content: String) {
        if (sDebugConfig.isDebuggable) {
            throw RuntimeException(format(tag, content))
        } else {
            println(format(tag, content))
        }
    }

    fun e(tag: String, content: String, e: Throwable) {
        e.printStackTrace()
        e(tag, content)
    }

    fun w(tag: String, content: String) {
        println(format(tag, content))
    }

    fun w(tag: String, content: String, e: Throwable) {
        w(tag, content)
        e.printStackTrace()
    }

    fun i(tag: String, content: String) {
        println(format(tag, content))
    }

    fun d(tag: String, content: String) {
        if (sDebugConfig.isDebuggable) {
            println(format(tag, content))
        } else {
        }
    }

    private fun format(tag: String, content: String): String {
        return sDateFormat.format(Date()) + ":" + tag + ":" + content
    }
}