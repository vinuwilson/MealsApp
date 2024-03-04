package com.example.mealdetails.presenter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MealDetailsViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val mealState = mutableStateOf<String?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_details") ?: ""
        mealState.value = mealId
    }
}