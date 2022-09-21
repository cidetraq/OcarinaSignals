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
import androidx.compose.ui.unit.dp

@Composable
fun SequenceEditor(updateSongSequences: (String, String) -> Unit) {
    var newSequenceName by rememberSaveable { mutableStateOf("") }
    var newSequence by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = newSequenceName,
            onValueChange = { newSequenceName = it },
            label = { Text("New Sequence Name") },
            singleLine = true, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        TextField(
            value = newSequence,
            onValueChange = { newSequence = it },
            label = { Text("New Sequence of Notes") },
            singleLine = true, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = { updateSongSequences(newSequenceName, newSequence) },
            modifier = Modifier.fillMaxWidth(.5f)
        ) {
            Text(text = "Submit New Sequence")
        }
    }
}
