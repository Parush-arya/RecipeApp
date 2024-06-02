package com.example.motamaker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _responseState = mutableStateOf(CategoriesData())
    var responseState: State<CategoriesData> = _responseState

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                var response = apiService.getCategories()
                _responseState.value = CategoriesData(
                    isLoading = false,
                    categories = response.categories
                )
            } catch (e: Exception) {
                println(e.message)
                _responseState.value = CategoriesData(status = 500, isLoading = false)
            } finally {

            }

        }
    }
}

data class CategoriesData(
    var isLoading: Boolean = true,
    var categories: List<Category> = emptyList(),
    var status: Int = 200
)