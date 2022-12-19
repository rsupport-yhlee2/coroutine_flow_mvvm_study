package com.example.coroutineflow.di

import com.example.coroutineflow.data.network.NetworkService
import com.example.coroutineflow.data.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(service: NetworkService) = ArticleRepository(service)
}