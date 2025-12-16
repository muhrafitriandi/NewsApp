package com.yandey.data.client.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.java

@Singleton
class RetrofitServiceFactory @Inject constructor(private val okHttpClient: OkHttpClient) {
    private val retrofitCache = ConcurrentHashMap<String, Retrofit>()

    fun <T> create(service: Class<T>, baseUrl: String): T {
        val retrofit = retrofitCache.computeIfAbsent(baseUrl) {
            Retrofit
                .Builder()
                .baseUrl(it)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit.create(service)
    }

    inline fun <reified T> get(baseUrl: String): T = create(T::class.java, baseUrl)
}
