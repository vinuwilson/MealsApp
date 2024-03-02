package com.example.mealsapp

import com.example.mealsapp.data.model.Categories
import com.example.mealsapp.domain.MealsCategoriesUseCase
import com.example.mealsapp.presenter.MealsCategoriesViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MealsCategoriesViewModelShould {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private lateinit var viewModel: MealsCategoriesViewModel
    private val mealsCategoriesUseCase: MealsCategoriesUseCase = mock()
    private val categories = mock<Categories>()
    private val expected = Result.success(categories)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCategoryListFromRepository(): Unit = runBlocking {

        mockSuccessfulCase()

        viewModel.rememberMeals.value

        verify(mealsCategoriesUseCase, times(1)).getCategoryList()
    }

    @Test
    fun emitCategoryListFromRepository() = runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.getMeals().first())

    }

    @Test
    fun emitErrorWhenReceiveError() = runBlocking {

        mockFailureCase()

        assertEquals(exception, viewModel.getMeals().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase() {
        whenever(mealsCategoriesUseCase.getCategoryList()).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = MealsCategoriesViewModel(mealsCategoriesUseCase)
    }

    private suspend fun mockFailureCase() {
        whenever(mealsCategoriesUseCase.getCategoryList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        viewModel = MealsCategoriesViewModel(mealsCategoriesUseCase)
    }
}