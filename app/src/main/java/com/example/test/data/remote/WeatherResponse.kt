package com.example.test.data.remote

data class MinuteForecastResponse(

    val lat: Double,

    val lon: Double,

    val timezone: String,

    val data: List<MinuteForecast>

)

data class MinuteForecast(

    val dt: Long,

    val precipitation: Double?

)