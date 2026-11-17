package com.example.test.data.remote

import com.google.gson.annotations.SerializedName

//https://api.open-meteo.com/v1/forecast?
// latitude=59.871551&
// longitude=30.306622&
// minutely_15=rain,snowfall,precipitation&
// timezone=Europe%2FMoscow&
// forecast_minutely_15=4
data class WeatherResponse(

    @SerializedName("minutely_15")
    val minutely15: Minutely15
)

data class Minutely15(

    val time: List<String>,

    val rain: List<Double>,

    val snowfall: List<Double>,

    val precipitation: List<Double>,


)