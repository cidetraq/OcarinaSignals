package com.example.ocarinasignals

import Play
import SequenceEditor
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun Ocarina(instrumentName: String) {
    val navController = rememberNavController()
    var songSequences by rememberSaveable {
        mutableStateOf(
            mutableMapOf<String, String>(
                "C Major Triad" to "CEG",
                "The Lick" to "DEFGDECD"
            )
        )
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    fun toggleDrawer(){
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
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
            FloatingActionButton(
                onClick = {
                    toggleDrawer()
                }){
                Icon(Icons.Default.Menu, contentDescription = "Localized description")
            }
        })
    {
        Column() {
            NavHost(
                navController = navController,
                startDestination = Screens.Play.route
            ) {
                composable(Screens.Play.route) {
                    Play(instrumentName, songSequences)
                }
                composable(Screens.SequenceEditor.route) {
                    SequenceEditor() { newSequenceName: String, newSequence: String ->
                        songSequences[newSequenceName] = newSequence
                    }
                }
            }
        }
    }
}