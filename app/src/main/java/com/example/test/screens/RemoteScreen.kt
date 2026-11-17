package com.example.test.screens

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.data.remote.WeatherClient
import com.example.test.data.remote.WeatherRepository
import com.example.test.data.remote.WeatherResponse
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
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

    val errorMessage = remember {
        mutableStateOf<String?>(null)
    }

    val context = LocalContext.current

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }


    val locationPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->

        if (isGranted) {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location ->

                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    scope.launch {
                        errorMessage.value = null

                        try {
                            val result = repository.getWeather(
                                latitude = latitude,
                                longitude = longitude
                            )

                            weather.value = result

                            Log.d("Weather", "Получен ответ: $result")

                        } catch (e: Exception) {
                            errorMessage.value = e.message
                            Log.e("Weather", "Ошибка запроса", e)
                        }
                    }
                } else {
                    errorMessage.value = "Не удалось определить местоположение"
                }
            }.addOnFailureListener { e ->
                errorMessage.value = e.message
            }

        } else {
            errorMessage.value = "Нет разрешения на геолокацию"
        }
    }





    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Button(
                onClick = {
                    locationPermission.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
                    )
                    {
                    if (errorMessage.value != null) {
                        Text("Ошибка: ${errorMessage.value}")
                    } else {
                        Text("Press to make a call")
                    }
                }

        }

        weather.value?.let { result ->

            Text("Times: ${result.minutely15.time}")

            Text("Rain: ${result.minutely15.rain}")

            Text("Snowfall: ${result.minutely15.snowfall}")

            Text("Precipitation: ${result.minutely15.precipitation}")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RemoteScreen () {
    makeACall()
}