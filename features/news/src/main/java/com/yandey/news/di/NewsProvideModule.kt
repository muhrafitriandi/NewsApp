package com.yandey.news.di

import com.yandey.data.client.network.RetrofitServiceFactory
import com.yandey.news.data.api.NewsApiClient
import com.yandey.utils.routes.BaseUrlType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsProvideModule {
    @Provides
    @Singleton
    fun providesNewsApiClient(factory: RetrofitServiceFactory): NewsApiClient = factory.get(BaseUrlType.NEWS.url)
}