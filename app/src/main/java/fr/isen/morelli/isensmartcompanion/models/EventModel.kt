package fr.isen.morelli.isensmartcompanion.models

import java.io.Serializable

data class EventModel(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val category: String,
): Serializable {
    companion object {
        fun fakeEvents(): List<EventModel> {
            return listOf(
                EventModel("id", "title", "description", "date", "location", "category")
            )
        }
    }
}
