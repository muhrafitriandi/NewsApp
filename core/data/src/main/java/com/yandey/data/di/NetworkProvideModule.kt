package com.yandey.data.di

import android.content.Context
import com.chuckerteam.chucker.api.BodyDecoder
import com.yandey.data.chucker.decoder.CompositeBodyDecoder
import com.yandey.data.chucker.decoder.GzipBodyDecoder
import com.yandey.data.client.network.OkHttpClientProvider
import com.yandey.data.interceptor.ChuckerInterceptor
import com.yandey.data.interceptor.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import com.chuckerteam.chucker.api.ChuckerInterceptor as ChuckerInterceptorApi

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvideModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClientProvider: OkHttpClientProvider): OkHttpClient = okHttpClientProvider.create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
        decoder: BodyDecoder,
    ): ChuckerInterceptorApi = ChuckerInterceptor.create(context, decoder)

    @Provides
    @Singleton
    fun provideBodyDecoder(): BodyDecoder =
        CompositeBodyDecoder(
            listOf(
                GzipBodyDecoder(),
                // BrotliBodyDecoder(), // optional
            ),
        )
}