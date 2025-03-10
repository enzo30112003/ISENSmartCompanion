package fr.isen.morelli.isensmartcompanion.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.isen.bouchut.isensmartcompanion.R
import fr.isen.bouchut.isensmartcompanion.viewmodel.MainViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    var userInput = remember { mutableStateOf("") }
    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 15.dp)) {
        Image(
            painter = painterResource(id = R.drawable.isen),
            contentDescription = stringResource(id = R.string.title_image),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp),
            contentScale = ContentScale.Fit
        )

        Text(context.getString(R.string.app_name) )
//        Text("Smart Companion")
        Text(stringResource(id=R.string.Welcoming_message))
        Spacer(Modifier.weight(0.5f))

        Row {
            TextField(userInput.value, { newValue ->
                userInput.value = newValue
            })
            Button(
                onClick = {
                    val content = userInput.value.trim()
                    if (content.isNotEmpty()) {
                        viewModel.addHistoryEntry(content)
                        userInput.value = ""
                        Toast.makeText(context, R.string.add_history, Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.send), // Image du bouton
                    contentDescription = stringResource(id = R.string.button)
                )
            }
        }
        }
    }

