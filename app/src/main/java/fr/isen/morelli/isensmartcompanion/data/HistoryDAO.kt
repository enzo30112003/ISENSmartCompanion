package fr.isen.morelli.isensmartcompanion.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntry: HistoryEntry)

    @Query("SELECT * FROM history_table ORDER BY id DESC")
    fun getAllHistory(): LiveData<List<HistoryEntry>>

    @Query("DELETE FROM history_table WHERE id = :historyId")
    suspend fun delete(historyId: Int)
}

