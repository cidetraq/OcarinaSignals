package com.example.ocarinasignals

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Ocarina(instrumentName: String){
    Text(text = "Play $instrumentName")
}