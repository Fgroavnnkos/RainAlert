package com.example.test.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        weather.value = repository.getWeather(
                            latitude = 59.871551,
                            longitude = 30.306622
                        )
                    }
                },
            ) {
                Text("push to make a Call")
            }

        }

        val rain = weather.value?.minutley_15?.rain?.getOrNull(0)
        Text("Rain: ${rain ?: "No data"} mm")
    }
}
@Preview(showBackground = true)
@Composable
fun RemoteScreen () {
    makeACall()
}