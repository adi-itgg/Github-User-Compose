package me.syahdilla.putra.sholeh.githubusercompose.utils

import android.util.Log
import kotlin.reflect.KClass

class LogggerFactory(
    private val tag: String,
    private val isTestMode: Boolean = false
) {

    constructor(clazz: KClass<*>, isTestMode: Boolean = false) : this(clazz.java.simpleName, isTestMode)

    enum class Level(val value: Int) {
        DEBUG(3),
        ERROR(6)
    }

    private val testFormat = "%lvl/%tag: [%thread] %logs"
    private val format = "[%thread] %logs"

    private fun sendLog(level: Level, logs: String) {

        if (isTestMode)
            println(testFormat.replace("%lvl", level.name[0].toString())
                .replace("%thread", Thread.currentThread().name)
                .replace("%logs", logs)
                .replace("%tag", tag))
        else
            Log.println(level.value, tag, format
                .replace("%thread", Thread.currentThread().name)
                .replace("%logs", logs)
            )
    }

    private fun sendLog(level: Level, logs: List<String?>) {
        if (logs.size == 1) return sendLog(level, logs[0].toString())
        val sb = StringBuilder()
        logs.forEach {
            if (sb.isNotEmpty()) sb.append(" ")
            sb.append(it)
        }
        sendLog(level, sb.toString())
        sb.setLength(0)
    }

    fun debug(vararg logs: String?) =
        sendLog(Level.DEBUG, logs.toList())
    fun debug(logs: () -> String) =
        debug(logs())

    fun error(vararg logs: String?) =
        sendLog(Level.ERROR, logs.toList())
    fun error(logs: () -> String) =
        error(logs())

}

inline fun<reified T: Any> T.loggerFactory(isTestMode: Boolean = false) = lazy(LazyThreadSafetyMode.NONE) {
    LogggerFactory(this::class, isTestMode)
}
