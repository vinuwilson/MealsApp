package com.example.mealsapp

import com.example.mealsapp.mealslist.data.api.MealsRepositoryImp
import com.example.mealsapp.mealslist.data.api.MealsServices
import com.example.mealsapp.mealslist.data.model.Categories
import com.example.mealsapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class MealsRepositoryShould : BaseUnitTest(){


    private lateinit var repository: MealsRepositoryImp
    private val service : MealsServices = mock()
    private val categories = com.nhaarman.mockitokotlin2.mock<Categories>()
    private val expected = Result.success(categories)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getCategoryListFromService(): Unit = runBlocking {

        mockSuccessfulCase()

        repository.getCategoryList()

        verify(service, times(1)).getCategoryList()
    }

    @Test
    fun emitCategoryListFromService() =  runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, repository.getCategoryList().first())
    }

    @Test
    fun emitErrorWhenReceiveError() = runBlocking {

        mockFailureCase()

        assertEquals(exception, repository.getCategoryList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.getCategoryList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        repository = MealsRepositoryImp(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getCategoryList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        repository = MealsRepositoryImp(service)
    }
}