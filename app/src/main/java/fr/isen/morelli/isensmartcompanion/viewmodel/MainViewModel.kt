package fr.isen.bouchut.isensmartcompanion.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.isen.morelli.isensmartcompanion.data.HistoryDatabase
import fr.isen.morelli.isensmartcompanion.data.HistoryEntry
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val historyDao = HistoryDatabase.getDatabase(application).historyDao()

    val historyList: LiveData<List<HistoryEntry>> = historyDao.getAllHistory()

    fun addHistoryEntry(content: String) {
        viewModelScope.launch {
            val entry = HistoryEntry(content = content)
            historyDao.insert(entry)
            Log.d("History", "Entrée ajoutée : $entry")
        }
    }
    fun deleteHistoryEntry(id: Int) {
        viewModelScope.launch {
            historyDao.delete(id)
        }
    }

}
