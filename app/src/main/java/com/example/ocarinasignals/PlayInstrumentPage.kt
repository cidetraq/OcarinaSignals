package com.example.ocarinasignals

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@Composable
fun Ocarina(instrumentName: String){
    Column() {
        var playedNote by rememberSaveable { mutableStateOf("A") }

        Text(text = "Play $instrumentName")
        Text(text = "Note $playedNote played")
        Row(modifier= Modifier.padding(20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Button(onClick = { playedNote="A" }, modifier=Modifier.width(100.dp)) {
                Text(text = "A")
            }
            Button(onClick = { playedNote="A#" }, modifier=Modifier.width(100.dp)) {
                Text(text = "A#")
            }
            Button(onClick = { playedNote="B" }, modifier=Modifier.width(100.dp) ) {
                Text(text = "B")
            }
        }
        Row(){

        }
        Row(){

        }
    }

}