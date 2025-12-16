package com.yandey.data.chucker

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.RetentionManager
import com.yandey.data.BuildConfig

object ChuckerCollectorProvider {
    fun create(context: Context): ChuckerCollector =
        ChuckerCollector(
            context = context,
            showNotification = BuildConfig.DEBUG,
            retentionPeriod = RetentionManager.Period.ONE_DAY,
        )
}
