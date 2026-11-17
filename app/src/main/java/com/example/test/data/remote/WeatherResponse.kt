package com.example.test.data.remote
//https://api.open-meteo.com/v1/forecast?
// latitude=59.871551&
// longitude=30.306622&
// minutely_15=rain,snowfall,precipitation&
// timezone=Europe%2FMoscow&
// forecast_minutely_15=4
data class WeatherResponse(

    val minutley_15: Minutley15
)

data class Minutley15(

    val time: List<String>,

    val rain: List<Double>,

    val snowfall: List<Double>,

    val percipitation: List<Double>,


)