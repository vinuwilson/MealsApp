package com.example.mealsapp.presenter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealsapp.ui.theme.MealsAppTheme

@Composable
fun MealsCategoriesScreen(name: String, modifier: Modifier = Modifier) {
    val viewModel: MealsCategoriesViewModel = viewModel()

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
        MealsCategoriesScreen("Android")
    }
}