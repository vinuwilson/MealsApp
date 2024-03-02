package com.example.mealsapp.presenter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.data.model.Categories
import com.example.mealsapp.data.model.Category
import com.example.mealsapp.domain.MealsCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsCategoriesViewModel @Inject constructor(
    private val mealsCategoriesUseCase: MealsCategoriesUseCase
) : ViewModel() {

    val mealsState = mutableStateOf(emptyList<Category>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals.first().getOrNull()!!.categories
        }
    }

    suspend fun getMeals(): Flow<Result<Categories>> {
        return mealsCategoriesUseCase.getCategoryList()
    }
}
