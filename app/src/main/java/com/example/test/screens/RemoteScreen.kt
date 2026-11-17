package com.example.test.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.data.remote.WeatherClient
import com.example.test.data.remote.WeatherRepository
import com.example.test.data.remote.WeatherResponse
import kotlinx.coroutines.launch

@Composable
fun makeACall() {

    val repository =  remember {
        WeatherRepository(
            weatherApi = WeatherClient.api
        )
    }

    val scope = rememberCoroutineScope()

    val weather = remember {
        mutableStateOf<WeatherResponse?>(null)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Button(
                onClick = {
                    scope.launch {

                        try {

                            weather.value = repository.getWeather(
                                latitude = 59.871551,
                                longitude = 30.306622
                            )
                        } catch (e: Exception) {

                            Log.e("Weather", "Ошибка запроса", e)

                        }
                    }
                },
            ) {
                Text("push to make a Call")
            }

        }

        val rain = weather.value?.minutely15?.rain?.getOrNull(0)
        Text("Rain: ${rain ?: "No data"} mm")
    }
}
@Preview(showBackground = true)
@Composable
fun RemoteScreen () {
    makeACall()
}