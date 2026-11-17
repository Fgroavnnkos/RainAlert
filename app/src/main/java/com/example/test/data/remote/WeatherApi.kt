package com.example.test.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/4.0/onecall/timeline/1min")
    suspend fun getMinuteForecast(

        @Query("lat")
        latitude: Double,

        @Query("lon")
        longitude: Double,

        @Query("appid")
        apiKey: String,

        @Query("lang")
        language: String = "ru"

    ): Response<MinuteForecastResponse>
}