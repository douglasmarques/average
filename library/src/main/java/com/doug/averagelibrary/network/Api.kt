package com.doug.averagelibrary.network

import retrofit2.http.GET

internal interface Api {

    companion object {
        const val URL = "https://roktcdn1.akamaized.net/store/test/android/"
    }

    @GET("prestored.json")
    suspend fun getPreStoredArray(): IntArray

}

