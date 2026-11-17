package com.example.test.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.data.remote.WeatherClient
import com.example.test.data.remote.WeatherRepository
import com.example.test.data.remote.WeatherResponse
import kotlinx.coroutines.launch

@Composable
fun Intro() {

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 16.dp, top = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Вести с API",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = "Тут можно вручную повзаимодействовать с api. " +
                        "Учтите, что из-за блокировок запросы могут не доходить до API.",
                style = MaterialTheme.typography.bodyLarge
            )

        }

    }
@Composable
fun MakeACall() {

    val repository = remember {
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

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onBackground
            ),

            ) {
            Column(
                modifier = Modifier.padding(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                weather.value?.let { result ->

                    for (i in result.minutely15.time.indices) {

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(all = 8.dp)
                                    .fillMaxWidth(),


                                ) {
                                ProvideTextStyle(
                                    value = MaterialTheme.typography.bodyLarge
                                ) {
                                    Text(
                                        text = "${result.minutely15.time[i].substringBefore('T')}  ${
                                            result.minutely15.time[i].substringAfter('T')
                                        }",
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text("Дождь: ${result.minutely15.rain[i]}")
                                    Text("Снег: ${result.minutely15.snowfall[i]}")
                                }
                            }
                        }
                    }
                }
            }
        }

            Button(
                onClick = {
                    scope.launch {

                        try {

                            val result = repository.getWeather(
                                latitude = 59.871551,
                                longitude = 30.306622
                            )

                            weather.value = result

                        } catch (e: Exception) {

                            Log.e("Weather", "Ошибка запроса", e)

                        }
                    }
                }
            ) {
                Text("push me")
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun RemoteScreen () {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Intro()
        MakeACall()
    }
}
