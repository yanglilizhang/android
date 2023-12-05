@file:Suppress("unused")

package com.leovp.kotlin.exts

import java.util.concurrent.TimeUnit

/**
  * Author: Michael Leo
  * Date: 2023/12/5 10:35
  */
fun Long.formatTimestamp(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours)
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
