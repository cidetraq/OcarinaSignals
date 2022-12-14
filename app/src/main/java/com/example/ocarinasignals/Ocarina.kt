package com.example.ocarinasignals

import Play
import SequenceEditor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch

sealed class Screens(val title: String, val route: String) {
    object Play : Screens("Play Ocarina", "play")
    object SequenceEditor : Screens("Edit Sequences", "editSequence")
}

@Composable
fun OpenSequenceEditor(
    songSequences: MutableMap<String, String>,
    recordedSequence: String, updateRecordedSequence: (String) -> Unit, toggleRecordingMode: () ->
    Unit, recordingModeActive: Boolean
) {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false) }
            var iconColor = if (recordingModeActive) Color.Red else Color.Blue
            FloatingActionButton(
                onClick = {
                    if (recordingModeActive)
                        openDialog.value = true
                    toggleRecordingMode()
                }) {
                Icon(
                    Icons.Default.RecordVoiceOver, contentDescription = "Recording Toggle",
                    tint = iconColor
                )
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Sequence Editor")
                    },
                    text = {
                        SequenceEditor(songSequences = songSequences, recordedSequence = recordedSequence,
                            updateRecordedSequence = { updateRecordedSequence(it) },
                            updateSongSequences = { newSequenceName: String, newSequence: String ->
                                songSequences[newSequenceName] = newSequence
                            })
                    },
                    confirmButton = {
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                updateRecordedSequence("")
                                openDialog.value = false
                            }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Ocarina(instrumentName: String) {
    val navController = rememberNavController()
    var recordingModeActive by rememberSaveable() {
        mutableStateOf(false)
    }
    var songSequences by rememberSaveable {
        mutableStateOf(
            mutableMapOf<String, String>(
                "C Major Triad" to "CEG",
                "The Lick" to "DEFGDECD"
            )
        )
    }
    var recordedSequence by rememberSaveable { mutableStateOf("") }
    fun updateRecordedSequence(note: String) {
        if (note != "")
            recordedSequence += note
        else
            recordedSequence = ""
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    fun toggleDrawer() {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Text("Ocarina Signals", modifier = Modifier.padding(16.dp))
            Divider()
            Text("Main view", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController
                        .navigate(Screens.Play.route)
                    toggleDrawer()
                })
            Text("Sequences", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController
                        .navigate(Screens.SequenceEditor.route)
                    toggleDrawer()
                })
        },
        floatingActionButton = {
            Row() {
                FloatingActionButton(
                    onClick = {
                        toggleDrawer()
                    }) {
                    Icon(Icons.Default.Menu, contentDescription = "Drawer toggle")
                }
                OpenSequenceEditor(
                    songSequences = songSequences,
                    recordingModeActive = recordingModeActive,
                    recordedSequence = recordedSequence,
                    updateRecordedSequence = { updateRecordedSequence(it) },
                    toggleRecordingMode = { recordingModeActive = !recordingModeActive })
            }

        })
    {
        Column() {
            NavHost(
                navController = navController,
                startDestination = Screens.Play.route
            ) {
                composable(Screens.Play.route) {
                    Play(instrumentName, songSequences, recordingModeActive, recordedSequence,
                        { it -> updateRecordedSequence(it) })
                }
                composable(Screens.SequenceEditor.route) {
                    SequenceEditor(songSequences = songSequences, {newSequenceName: String, newSequence: String ->
                        songSequences[newSequenceName] = newSequence
                    }, recordedSequence) { it:String -> updateRecordedSequence(it) }
                }
            }
        }
    }
}