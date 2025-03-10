package fr.isen.morelli.isensmartcompanion.network

import fr.isen.morelli.isensmartcompanion.models.EventModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("events.json")
    fun getEvents(): Call<List<EventModel>>
}
