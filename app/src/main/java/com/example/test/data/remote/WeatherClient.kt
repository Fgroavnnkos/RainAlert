package com.example.test.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {
//https://api.open-meteo.com/v1/forecast?
    // latitude=59.871551&
    // longitude=30.306622&
    // minutely_15=rain,snowfall,precipitation&
    // timezone=Europe%2FMoscow&
    // forecast_minutely_15=4
    private const val BASE_URL =
        "https://api.open-meteo.com/"

    val api: WeatherApi by lazy {

        Retrofit.Builder()

            .baseUrl(BASE_URL)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()
            .create(WeatherApi::class.java)
    }
}