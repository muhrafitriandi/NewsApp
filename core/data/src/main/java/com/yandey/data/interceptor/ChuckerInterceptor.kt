package com.yandey.data.interceptor

import android.content.Context
import com.chuckerteam.chucker.api.BodyDecoder
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.yandey.data.chucker.ChuckerCollectorProvider

object ChuckerInterceptor {
    fun create(context: Context, decoder: BodyDecoder): ChuckerInterceptor =
        ChuckerInterceptor
            .Builder(context)
            .collector(ChuckerCollectorProvider.create(context))
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .addBodyDecoder(decoder)
            .createShortcut(true)
            .build()
}