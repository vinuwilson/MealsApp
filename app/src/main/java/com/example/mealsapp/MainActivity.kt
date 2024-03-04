package com.example.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealdetails.presenter.MealDetailsScreen
import com.example.mealdetails.presenter.MealDetailsViewModel
import com.example.mealsapp.presenter.MealsCategoriesScreen
import com.example.mealsapp.ui.theme.MealsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealsApp()
                }
            }
        }
    }
}

@Composable
fun MealsApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen { navigationMealDetails ->
                navController.navigate("destination_meal_details/${navigationMealDetails}")
            }
        }

        composable(
            route = "destination_meal_details/{meal_category_details}",
            arguments = listOf(navArgument("meal_category_details") {
                type = NavType.StringType
            })
        ) {
            val viewModel : MealDetailsViewModel = hiltViewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }
}
