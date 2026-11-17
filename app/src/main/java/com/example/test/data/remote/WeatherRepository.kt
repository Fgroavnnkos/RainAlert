package com.example.test.data.remote

import com.example.test.data.remote.WeatherApi
import com.example.test.data.remote.WeatherResponse
import java.io.IOException

class WeatherRepository(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {

        val response = weatherApi.getForecast(
            latitude = latitude,
            longitude = longitude
        )

        if (!response.isSuccessful) {
            throw IOException(
                "Ошибка сервера: ${response.code()}"
            )
        }

        return response.body()
            ?: throw IOException("Пустой ответ сервера")
    }
}