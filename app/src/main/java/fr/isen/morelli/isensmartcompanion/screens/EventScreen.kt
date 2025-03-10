package fr.isen.morelli.isensmartcompanion.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.isen.morelli.isensmartcompanion.EventDetailActivity
import fr.isen.morelli.isensmartcompanion.models.EventModel
import fr.isen.morelli.isensmartcompanion.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EventsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var events = remember { mutableStateOf<List<EventModel>>(listOf()) }
//    Button(
//        onClick = {
//            val intent = Intent(context, EventDetailActivity::class.java)
//            context.startActivity(intent)
//        },
//        content = {
//            Text("button")
//        })
    LaunchedEffect(Unit) {
        // Appels reseaux
        val call = NetworkManager.api.getEvents()
        call.enqueue(object: Callback<List<EventModel>> {
            override fun onResponse(
                call: Call<List<EventModel>>,
                response: Response<List<EventModel>>
            ) {
                events.value = response.body() ?: listOf()
            }

            override fun onFailure(call: Call<List<EventModel>>, t: Throwable) {
//                Log.e("request", t.message ?: "")
            }
        })
    }
    LazyColumn(modifier = modifier) {
        items(events.value) {
            EventRow(it)
        }
    }
}

@Composable
fun EventRow(event: EventModel) {
    val context = LocalContext.current
    Card(Modifier.padding(16.dp).clickable {
        val intent = Intent(context, EventDetailActivity::class.java).apply {
            putExtra("event", event)
        }
        context.startActivity(intent)
    })  {
        Text(event.title, Modifier.padding(16.dp))

    }
}