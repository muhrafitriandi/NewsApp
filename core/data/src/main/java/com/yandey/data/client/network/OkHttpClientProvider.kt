package com.yandey.data.client.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.yandey.data.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.apply

@Singleton
class OkHttpClientProvider @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val httpLoggingInterceptor: HttpLoggingInterceptor,
    private val chuckerInterceptor: ChuckerInterceptor,
) {
    fun create(): OkHttpClient =
        OkHttpClient
            .Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(httpLoggingInterceptor)
                    addInterceptor(chuckerInterceptor)
                }
                retryOnConnectionFailure(true)
                followRedirects(false)
                followSslRedirects(false)
                connectTimeout(30, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
                dispatcher(
                    Dispatcher().apply {
                        maxRequests = 20
                        maxRequestsPerHost = 10
                    },
                )
            }.build()
}