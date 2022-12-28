import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


fun showMessageOnNewSequenceAdded(newSequenceName: String, context: Context) {
    val text = "New sequence ${newSequenceName} added"
    val duration = Toast.LENGTH_SHORT
    val toast = Toast.makeText(context, text, duration)
    toast.show()
}

fun hostButtonPress(
    updateSongSequences: (String, String) -> Unit, newSequenceName: String,
    newSequence: String, context: Context, updateRecordedSequence: (String) -> Unit
) {
    updateSongSequences(newSequenceName, newSequence)
    showMessageOnNewSequenceAdded(newSequenceName, context)
    updateRecordedSequence("")
}

@Composable
fun SequenceEditor(
    songSequences: MutableMap<String, String>,
    updateSongSequences: (String, String) -> Unit,
    recordedSequence: String,
    updateRecordedSequence: (String) -> Unit
) {
    var newSequenceName by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        TextField(
            value = newSequenceName,
            onValueChange = { newSequenceName = it },
            label = { Text("New Sequence Name") },
            singleLine = true, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        TextField(
            value = recordedSequence,
            onValueChange = { },
            label = { Text("New Sequence of Notes") },
            singleLine = true, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = {
                hostButtonPress(
                    updateSongSequences, newSequenceName, recordedSequence,
                    context, updateRecordedSequence
                )
            },
            modifier = Modifier.fillMaxWidth(.5f)
        ) {
            Text(text = "Submit New Sequence")
        }
        Text(songSequences.toString())
    }
}
