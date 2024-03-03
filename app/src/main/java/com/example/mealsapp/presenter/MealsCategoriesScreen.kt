package com.example.mealsapp.presenter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mealsapp.ui.theme.MealsAppTheme

@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = hiltViewModel()

    val meals = viewModel.mealsState.value

    LazyColumn {
        items(meals) { meal ->
            Text(text = meal.strCategory)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealsAppTheme {
        MealsCategoriesScreen()
    }
}