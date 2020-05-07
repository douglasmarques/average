package com.doug.averagelibrary.functionality

import com.doug.averagelibrary.di.AverageKoinContext
import com.doug.averagelibrary.repository.AverageRepository

internal object LoadPreStoredArray {
    private val repository: AverageRepository by AverageKoinContext.koin.inject()

    suspend fun run() = repository.loadPreStoredArray()
}
