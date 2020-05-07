package com.doug.averagelibrary

import android.app.Application
import com.doug.averagelibrary.di.AverageKoinContext
import com.doug.averagelibrary.functionality.AddNumber
import com.doug.averagelibrary.functionality.GetAverage
import com.doug.averagelibrary.functionality.LoadPreStoredArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Average {

    fun init(context: Application) {
        AverageKoinContext.init(context)
        loadPreStoredArray()
    }

    private fun loadPreStoredArray() {
        GlobalScope.launch(Dispatchers.IO) {
            LoadPreStoredArray.run()
        }
    }

    fun addNumber(number: Int) = AddNumber.run(number)

    fun getAverage() = GetAverage.run()
}
