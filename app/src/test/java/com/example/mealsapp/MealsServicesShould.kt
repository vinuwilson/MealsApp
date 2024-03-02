package com.example.mealsapp

import com.example.mealsapp.data.api.MealsAPI
import com.example.mealsapp.data.api.MealsServices
import com.example.mealsapp.data.model.Categories
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MealsServicesShould {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    private lateinit var service: MealsServices
    private val api: MealsAPI = mock()
    private val categories = mock<Categories>()
    private val expected = Result.success(categories)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCategoryListFromAPI(): Unit = runBlocking {

        service = MealsServices(api)

        service.getCategoryList().first()

        verify(api, times(1)).fetchAllCategoryList()
    }

    @Test
    fun convertValuesToFlowResultAndEmit() = runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, service.getCategoryList().first())
    }

    @Test
    fun emitsErrorWhenNetworkFails() = runBlocking {

        mockFailureCase()

        assertEquals(exception.message, service.getCategoryList().first().exceptionOrNull()?.message)
    }


    private suspend fun mockSuccessfulCase() {

        whenever(api.fetchAllCategoryList()).thenReturn(categories)

        service = MealsServices(api)
    }

    private suspend fun mockFailureCase() {

        whenever(api.fetchAllCategoryList()).thenThrow(RuntimeException("Backend Exception"))

        service = MealsServices(api)
    }
}