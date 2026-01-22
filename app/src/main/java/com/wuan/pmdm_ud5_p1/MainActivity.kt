package com.wuan.pmdm_ud5_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wuan.pmdm_ud5_p1.ui.theme.PMDMud5p1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMud5p1Theme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LightSensorScreen()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                ) {
                    AccelerometerScreen()
                }
            }
        }
    }
}
