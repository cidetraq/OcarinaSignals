package com.example.ocarinasignals

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun Ocarina(instrumentName: String){
    var scrollstate = ScrollState(0)
    Column(modifier=Modifier.verticalScroll(scrollstate)) {
        var playedNote by rememberSaveable { mutableStateOf("") }
        var playedSequence by rememberSaveable { mutableStateOf("") }
        var songSequences by rememberSaveable { mutableStateOf(mutableMapOf<String, String>("C Major Triad" to "CEG", "The Lick" to "DEFGDECD")) }
        var isMatch by rememberSaveable { mutableStateOf(false) }
        Text(text = "Play $instrumentName")
        Text(text = "Note $playedNote played")
        Text(text = playedSequence)
        if (isMatch){
            var sequenceName = songSequences.filterValues{it == playedSequence}.keys.elementAt(0)
            Text(text = "You played ${sequenceName}!")
        }
        fun determineIfMatchingSequences(){
            playedSequence += playedNote
            var foundMatches = songSequences.values.filter { it.contains(playedSequence) }
            if (foundMatches.isEmpty()){
                playedSequence = ""
            }
            if (playedSequence in songSequences.values){
                isMatch = true
            }
        }
        fun clickNote(note: String){
            playedNote = note
            isMatch = false
            determineIfMatchingSequences()
        }

        Column(){
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


        var newSequenceName by rememberSaveable { mutableStateOf("") }
        var newSequence by rememberSaveable { mutableStateOf("") }

        fun submitNewSequence(){
            songSequences[newSequenceName] = newSequence
        }
        Column(modifier= Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            TextField(
                value = newSequenceName,
                onValueChange = { newSequenceName = it },
                label = { Text("New Sequence Name") },
                singleLine = true, modifier=Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(20.dp))
            TextField(
                value = newSequence,
                onValueChange = { newSequence = it },
                label = { Text("New Sequence of Notes") },
                singleLine = true, modifier=Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = { submitNewSequence() }, modifier=Modifier.fillMaxWidth(.5f)) {
                Text(text = "Submit New Sequence")
            }
        }



    }

}