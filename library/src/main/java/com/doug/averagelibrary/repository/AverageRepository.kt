package com.doug.averagelibrary.repository

import android.content.SharedPreferences
import com.doug.averagelibrary.network.Api

internal class AverageRepository(
    private val api: Api,
    private val preferences: SharedPreferences
) {

    companion object {
        const val NUMBER_ARRAY_KEY = "number_array_key"
    }

    suspend fun loadPreStoredArray() {
        if (!preferences.contains(NUMBER_ARRAY_KEY)) {
            val array = api.getPreStoredArray()
            persistNumberArray(array)
        }
    }

    fun addNumber(number: Int) {
        if (preferences.contains(NUMBER_ARRAY_KEY)) {
            val numbers = getPersistedArray().toMutableList()
            numbers.add(number)
            persistNumberArray(numbers.toIntArray())
        } else {
            throw initialisationException()
        }
    }

    fun getNumberArray(): List<Int> {
        if (preferences.contains(NUMBER_ARRAY_KEY)) {
            return getPersistedArray()
        } else {
            throw initialisationException()
        }
    }

    private fun initialisationException() = IllegalStateException(
        "Array not loaded, make sure you called Average.init() on your Application class."
    )

    private fun getPersistedArray(): List<Int> = preferences
        .getString(NUMBER_ARRAY_KEY, null)?.split(",")?.map { item ->
            item.toInt()
        } ?: emptyList()

    private fun persistNumberArray(array: IntArray) {
        val stringArray = array.joinToString(",")
        preferences.edit()
            .putString(NUMBER_ARRAY_KEY, stringArray)
            .apply()
    }
}
