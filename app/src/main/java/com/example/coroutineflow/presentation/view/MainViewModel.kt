package com.example.coroutineflow.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineflow.data.entity.Article
import com.example.coroutineflow.data.repository.ArticleRepository
import com.example.coroutineflow.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ArticleRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Uninitialized)
    val uiState: StateFlow<UiState> = _uiState
    fun fetchData() = viewModelScope.launch {
        repository.getAllArticles()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Uninitialized
            ).collect {
                _uiState.value = UiState.Success(it as List<Article>)
            }
    }
}