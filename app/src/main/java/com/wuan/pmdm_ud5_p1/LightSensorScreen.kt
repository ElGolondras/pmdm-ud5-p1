package com.wuan.pmdm_ud5_p1

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun LightSensorScreen() {
    val context = LocalContext.current

    var lux by remember { mutableStateOf(0f) }

    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    val lightSensor = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    DisposableEffect(lightSensor) {
        if (lightSensor == null) {
            onDispose { }
        } else {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    lux = event.values[0]
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    // opcional
                }
            }

            sensorManager.registerListener(
                listener,
                lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            onDispose {
                sensorManager.unregisterListener(listener)
            }
        }
    }

    val (etiqueta, backgroundColor, textColor) = when {
        lux < 10f -> Triple("Oscuro", Color.DarkGray, Color.Black)
        lux < 100f -> Triple("Luz normal", Color.White, Color.Black)
        else -> Triple("Muy iluminado", Color.Yellow, Color.Black)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "Sensor de luz",
            style = MaterialTheme.typography.titleLarge,
            color = textColor
        )

        if (lightSensor == null) {
            Text(
                "Este dispositivo no tiene sensor de luz.",
                color = textColor
            )
        } else {
            Text("Lux: $lux", color = textColor)
            Text(
                "Estado: $etiqueta",
                style = MaterialTheme.typography.titleMedium,
                color = textColor
            )
        }
    }
}