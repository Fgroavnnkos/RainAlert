package com.example.test.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
//https://api.open-meteo.com/v1/forecast?
    // latitude=59.871551&
    // longitude=30.306622&
    // minutely_15=rain,snowfall,precipitation&
    // timezone=Europe%2FMoscow&
    // forecast_minutely_15=4
    @GET("v1/forecast?" +
        "minutely_15=rain,snowfall,precipitation" +
        "&timezone=auto" +
        "&forecast_minutely_15=4"
    )
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,

    ): Response<WeatherResponse>
}