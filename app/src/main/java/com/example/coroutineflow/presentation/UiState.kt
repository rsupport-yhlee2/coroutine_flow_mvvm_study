package com.example.coroutineflow.presentation

import com.example.coroutineflow.data.entity.Article

sealed class UiState{
    object Uninitialized: UiState()

    object Loading : UiState()

    data class Success(val data : List<Article>) : UiState()

    data class Error<T>(val error: Throwable) : UiState()
}
