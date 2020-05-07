package com.doug.averagelibrary.functionality

import android.content.Context
import com.doug.averagelibrary.di.AverageKoinContext
import com.doug.averagelibrary.di.apiModule
import com.doug.averagelibrary.di.repositoryModule
import com.doug.averagelibrary.di.retrofitModule
import com.doug.averagelibrary.repository.AverageRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAverageTest {

    @Before
    fun setUp() {
        val context = mock<Context>{
            on { applicationContext } doReturn mock()
        }
        AverageKoinContext.init(context)
    }

    @Test
    fun calculateAverage() {
        var array = listOf(2, 4, 6)
        assertEquals(4.0f, GetAverage.calculateAverage(array))

        array = listOf(1,2,3,4,5,6)
        assertEquals(3.5f, GetAverage.calculateAverage(array))

        array = listOf(0)
        assertEquals(0.0f, GetAverage.calculateAverage(array))

        array = listOf(-1,-2)
        assertEquals(-1.5f, GetAverage.calculateAverage(array))
    }

}
