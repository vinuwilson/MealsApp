package com.example.mealsapp.presenter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.data.model.Categories
import com.example.mealsapp.data.model.Category
import com.example.mealsapp.domain.MealsCategoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class MealsCategoriesViewModel(
    private val mealsCategoriesUseCase: MealsCategoriesUseCase
) : ViewModel() {

    val rememberMeals = mutableStateOf(emptyList<Category>())

    init {
        viewModelScope.launch {
            val meals = getMeals()
            rememberMeals.value = meals.first().getOrNull()!!.categories
        }
    }

    suspend fun getMeals(): Flow<Result<Categories>> {
        return mealsCategoriesUseCase.getCategoryList()
    }
}
