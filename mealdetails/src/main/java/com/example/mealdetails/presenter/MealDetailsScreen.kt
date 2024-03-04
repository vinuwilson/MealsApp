package com.example.mealdetails.presenter

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MealDetailsScreen(value: String?) {
    Text(
        text = value!!,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun MealDetailsScreenPreview() {
    MealDetailsScreen(null)
}