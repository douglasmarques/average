package com.doug.averageapp

import android.app.Application
import com.doug.averagelibrary.Average

class AverageApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Average.init(this)
    }
}
