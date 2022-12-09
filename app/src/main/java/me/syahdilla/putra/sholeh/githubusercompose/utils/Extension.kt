package me.syahdilla.putra.sholeh.githubusercompose.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import kotlinx.coroutines.CancellationException

/**
 * Try run wrapper from [runCatching] and safe for coroutine
 *
 * @see runCatching
 */
inline fun <T, R> T.tryRun(block: T.() -> R): Result<R> =
    runCatching(block).onFailure { if (it is CancellationException) throw it }


inline fun<reified T: Parcelable> Intent.getParcel() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        getParcelableExtra(T::class.java.simpleName, T::class.java)
    else @Suppress("DEPRECATION")
        getParcelableExtra(T::class.java.simpleName)