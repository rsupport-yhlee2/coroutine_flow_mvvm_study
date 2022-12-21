package com.example.coroutineflow.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coroutineflow.R
import com.example.coroutineflow.data.entity.Article
import com.example.coroutineflow.presentation.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("MainActivity","start")
        setObserver()
    }

    private fun setObserver() {
        Log.e("MainActivity","observer set")
        lifecycleScope.launch{
            viewModel.uiState.collect{ state ->
                Log.e("MainActivity","${state}")
                render(state)
            }
        }
    }

    private fun render(state: UiState) {
        when(state){
            is UiState.Uninitialized -> {viewModel.fetchData()}
            is UiState.Loading -> {}
            is UiState.Success -> handleSuccess(state)
            is UiState.Error -> {}
            else -> {}
        }
    }

    private fun handleSuccess(state: UiState.Success) {
        state.data.forEach {
            Log.e("Main","$it")
        }
    }


}