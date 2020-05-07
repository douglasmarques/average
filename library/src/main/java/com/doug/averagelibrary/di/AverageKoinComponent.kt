package com.doug.averagelibrary.di

import org.koin.core.Koin
import org.koin.core.KoinComponent

internal interface AverageKoinComponent : KoinComponent {
    override fun getKoin(): Koin = AverageKoinContext.koin
} 
