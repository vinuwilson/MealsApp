package com.example.mealsapp.mealslist.presenter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.mealslist.data.model.Categories
import com.example.mealsapp.mealslist.data.model.Category
import com.example.mealsapp.mealslist.domain.MealsCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsCategoriesViewModel @Inject constructor(
    private val mealsCategoriesUseCase: MealsCategoriesUseCase
) : ViewModel() {

    val mealsState = mutableStateOf(emptyList<Category>())
    private val _sate = MutableStateFlow(MealSate())
    val state = _sate.asStateFlow()

    init {
        viewModelScope.launch {
            _sate.update {
                it.copy(
                    category = getMeals().first().getOrNull()!!.categories
                )
            }
        }
    }

    suspend fun getMeals(): Flow<Result<Categories>> {
        return mealsCategoriesUseCase.getCategoryList()
    }
    data class MealSate(
        val category: List<Category> = emptyList()
    )
}
