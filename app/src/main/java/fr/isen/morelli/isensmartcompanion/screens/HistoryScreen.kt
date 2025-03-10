package fr.isen.morelli.isensmartcompanion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.isen.bouchut.isensmartcompanion.R
import fr.isen.bouchut.isensmartcompanion.viewmodel.MainViewModel


@Composable
fun HistoryScreen(viewModel: MainViewModel) {
    val historyList by viewModel.historyList.observeAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(stringResource(R.string.History), style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(historyList) { entry ->
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("- ${entry.content}", modifier = Modifier.weight(1f))

                    IconButton(onClick = { viewModel.deleteHistoryEntry(entry.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete))
                    }
                }
            }
        }
    }
}
