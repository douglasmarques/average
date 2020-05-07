package com.doug.averagelibrary.functionality

import com.doug.averagelibrary.di.AverageKoinComponent
import com.doug.averagelibrary.di.AverageKoinContext.koin
import com.doug.averagelibrary.repository.AverageRepository

internal object GetAverage : AverageKoinComponent {
    private val repository: AverageRepository by koin.inject()

    fun run() = calculateAverage(repository.getNumberArray())

    fun calculateAverage(array: List<Int>): Float {
        return array.sum().div(array.size.toFloat())
    }
}
