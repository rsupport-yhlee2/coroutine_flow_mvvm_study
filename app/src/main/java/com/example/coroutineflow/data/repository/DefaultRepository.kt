package com.example.coroutineflow.data.repository

import com.example.coroutineflow.data.entity.Article
import com.example.coroutineflow.presentation.UiState
import kotlinx.coroutines.flow.Flow

interface DefaultRepository {
    fun getAllArticles() : Flow<List<Article>>
}