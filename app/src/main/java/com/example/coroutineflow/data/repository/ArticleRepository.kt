package com.example.coroutineflow.data.repository

import com.example.coroutineflow.data.entity.Article
import com.example.coroutineflow.data.network.NetworkService
import com.example.coroutineflow.presentation.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepository(
    private val service: NetworkService
) : DefaultRepository {
    override fun getAllArticles(): Flow<List<Article>> = flow {
        val articles = service.getTopHeadlines("us").articles
        emit(articles)
    }
}