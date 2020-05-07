package com.doug.averagelibrary.functionality

import com.doug.averagelibrary.di.AverageKoinComponent
import com.doug.averagelibrary.di.AverageKoinContext
import com.doug.averagelibrary.repository.AverageRepository

internal object AddNumber : AverageKoinComponent {
    private val repository: AverageRepository by AverageKoinContext.koin.inject()

    fun run(number: Int) = repository.addNumber(number)
}
