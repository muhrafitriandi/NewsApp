package com.yandey.news.di

import com.yandey.news.data.repository.NewsRepositoryImpl
import com.yandey.news.data.source.remote.NewsRemoteDataSource
import com.yandey.news.data.source.remote.NewsRemoteDataSourceImpl
import com.yandey.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsBindModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(impl: NewsRepositoryImpl): NewsRepository
}
