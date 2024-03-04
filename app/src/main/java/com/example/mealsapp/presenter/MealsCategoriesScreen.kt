package com.example.mealsapp.presenter

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mealsapp.R
import com.example.mealsapp.data.model.Category
import com.example.mealsapp.ui.theme.MealsAppTheme

@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {
    val viewModel: MealsCategoriesViewModel = hiltViewModel()

    val meals = viewModel.mealsState.value

    LazyColumn(contentPadding = PaddingValues(dimensionResource(id = R.dimen.content_padding))) {
        items(meals) { meal ->
            MealCategory(meal, navigationCallback)
        }
    }

}

@Composable
fun MealCategory(meal: Category, navigationCallback: (String) -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_view_rounded_shape)),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.card_view_card_elevation)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.card_view_padding))
            .clickable {
                navigationCallback(meal.description)
            }

    ) {
        Row(modifier = Modifier.animateContentSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.profile_image_size))
                    .padding(dimensionResource(id = R.dimen.profile_image_padding))
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(dimensionResource(id = R.dimen.column_padding))
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = meal.description,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.alpha(0.5f),
                    maxLines = if (isExpanded) 10 else 4
                )
            }
            Icon(
                imageVector = if (isExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(R.string.expand_row_icon),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.icon_padding))
                    .align(
                        if (isExpanded)
                            Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    MealsAppTheme {
//        MealsCategoriesScreen()
    }
}