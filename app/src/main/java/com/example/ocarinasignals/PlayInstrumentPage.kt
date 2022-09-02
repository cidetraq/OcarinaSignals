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
        var playedNote by rememberSaveable { mutableStateOf("") }
        var playedSequence by rememberSaveable { mutableStateOf("DFDSFDSFSD") }
        var songSequences by rememberSaveable { mutableStateOf(mapOf<String, String>("C Major Triad" to "CEG", "The Lick" to "DEFGDECD")) }
        var isMatch by rememberSaveable { mutableStateOf(false) }
        Text(text = "Play $instrumentName")
        Text(text = "Note $playedNote played")
        Text(text = playedSequence)

        if (isMatch){
            Text(text = "You played a sequence!")
        }
//        LaunchedEffect(key1 = playedNote, block = {})
        fun determineIfMatchingSequences(note: String){
            var localSequence = playedSequence
            println("localSequence: $localSequence")
            playedNote = note
            localSequence += note
            playedSequence = localSequence
            println("localSequence2: $localSequence")
            println("songSequences.values" + songSequences.values)
            var foundMatches = songSequences.values.filter { it.contains(localSequence) }
            if (foundMatches.isEmpty()){
                playedSequence = ""
            }
//            if (!songSequences.containsValue(localSequence)){
//                        playedSequence = ""
//                    }
            if (localSequence in songSequences.values){
                isMatch = true
            }
        }
        fun clickNote(note: String){
//            playedNote = note
            determineIfMatchingSequences(note)
        }

        Row(modifier= Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Button(onClick = { clickNote("A") }, modifier=Modifier.width(100.dp)) {
                Text(text = "A")
            }
            Button(onClick = { clickNote("A#") }, modifier=Modifier.width(100.dp)) {
                Text(text = "A#")
            }
            Button(onClick = { clickNote("B") }, modifier=Modifier.width(100.dp) ) {
                Text(text = "B")
            }
        }
        Row(modifier= Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Button(onClick = { clickNote("C") }, modifier=Modifier.width(100.dp)) {
                Text(text = "C")
            }
            Button(onClick = { clickNote("C#") }, modifier=Modifier.width(100.dp)) {
                Text(text = "C#")
            }
            Button(onClick = { clickNote("D") }, modifier=Modifier.width(100.dp) ) {
                Text(text = "D")
            }
        }
        Row(modifier= Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Button(onClick = { clickNote("D#") }, modifier=Modifier.width(100.dp)) {
                Text(text = "D#")
            }
            Button(onClick = { clickNote("E") }, modifier=Modifier.width(100.dp)) {
                Text(text = "E")
            }
            Button(onClick = { clickNote("F") }, modifier=Modifier.width(100.dp) ) {
                Text(text = "F")
            }
        }
        Row(modifier= Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ){
            Button(onClick = { clickNote("F#") }, modifier=Modifier.width(100.dp)) {
                Text(text = "F#")
            }
            Button(onClick = { clickNote("G") }, modifier=Modifier.width(100.dp)) {
                Text(text = "G")
            }
            Button(onClick = { clickNote("G#") }, modifier=Modifier.width(100.dp) ) {
                Text(text = "G#")
            }
        }
    }

}