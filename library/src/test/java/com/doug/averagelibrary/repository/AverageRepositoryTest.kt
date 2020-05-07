package com.doug.averagelibrary.repository

import android.content.SharedPreferences
import com.doug.averagelibrary.network.Api
import com.doug.averagelibrary.repository.AverageRepository.Companion.NUMBER_ARRAY_KEY
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AverageRepositoryTest {

    private lateinit var mockApi: Api
    private lateinit var repository: AverageRepository
    private lateinit var sharedPreferences: SharedPreferences

    @Test
    fun `loadPreStoredArray - when loading first time should call api and persist array on sharedPreferences`() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        val mockEditor = mock<SharedPreferences.Editor> {
            on { putString(NUMBER_ARRAY_KEY, array.joinToString(",")) } doReturn this.mock
        }
        sharedPreferences = mock {
            on { contains(NUMBER_ARRAY_KEY) } doReturn false
            on { edit() } doReturn mockEditor
        }

        mockApi = mock {
            onBlocking { getPreStoredArray() } doReturn array
        }

        repository = AverageRepository(mockApi, sharedPreferences)

        runBlocking {
            repository.loadPreStoredArray()
            verify(sharedPreferences).contains(NUMBER_ARRAY_KEY)
            verify(mockApi).getPreStoredArray()
            verify(mockEditor).putString(NUMBER_ARRAY_KEY, array.joinToString(","))
        }
    }

    @Test
    fun `loadPreStoredArray - when array is persisted on shared prefs should not call api and do not persist`() {
        sharedPreferences = mock {
            on { contains(NUMBER_ARRAY_KEY) } doReturn true
        }
        mockApi = mock()
        repository = AverageRepository(mockApi, sharedPreferences)

        runBlocking {
            repository.loadPreStoredArray()
            verify(sharedPreferences).contains(NUMBER_ARRAY_KEY)
            verifyZeroInteractions(mockApi)
            verifyNoMoreInteractions(sharedPreferences)
        }
    }

    @Test
    fun `addNumber - when array is initialised adds the number on the array `() {
        val initialArray = intArrayOf(1, 2, 3, 4, 5).joinToString(",")
        val finalArray = intArrayOf(1, 2, 3, 4, 5, 6).joinToString(",")
        val mockEditor = mock<SharedPreferences.Editor> {
            on { putString(NUMBER_ARRAY_KEY, finalArray) } doReturn this.mock
        }

        sharedPreferences = mock {
            on { contains(NUMBER_ARRAY_KEY) } doReturn true
            on { getString(NUMBER_ARRAY_KEY, null) } doReturn initialArray
            on { edit() } doReturn mockEditor
        }

        repository = AverageRepository(mock(), sharedPreferences)
        repository.addNumber(6)

        verify(sharedPreferences).contains(NUMBER_ARRAY_KEY)
        verify(sharedPreferences).getString(NUMBER_ARRAY_KEY, null)
        verify(mockEditor).putString(NUMBER_ARRAY_KEY, finalArray)
    }

    @Test(expected = IllegalStateException::class)
    fun `addNumber - when array is not initialised throws exception`() {
        sharedPreferences = mock {
            on { contains(NUMBER_ARRAY_KEY) } doReturn false
        }

        repository = AverageRepository(mock(), sharedPreferences)
        repository.addNumber(6)

        verify(sharedPreferences).contains(NUMBER_ARRAY_KEY)
        verifyNoMoreInteractions(sharedPreferences)
    }

    @Test
    fun `getNumberArray - when array is initialised should return the array`() {
        val array = intArrayOf(2, 4, 6).joinToString(",")

        sharedPreferences = mock {
            on { contains(NUMBER_ARRAY_KEY) } doReturn true
            on { getString(NUMBER_ARRAY_KEY, null) } doReturn array
        }

        repository = AverageRepository(mock(), sharedPreferences)
        assertEquals(array, repository.getNumberArray())
    }

    @Test(expected = IllegalStateException::class)
    fun `getNumberArray - when array is not initialised throws exception`() {
        sharedPreferences = mock {
            on { contains(NUMBER_ARRAY_KEY) } doReturn false
        }

        repository = AverageRepository(mock(), sharedPreferences)
        repository.getNumberArray()

        verify(sharedPreferences).contains(NUMBER_ARRAY_KEY)
        verifyNoMoreInteractions(sharedPreferences)
    }
}
