package com.example.app.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.data.PhoneContact
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneContactsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: PhoneContact)

    @Update
    suspend fun update(item: PhoneContact)

    @Query("DELETE FROM phone_contacts WHERE id = :itemId")
    suspend fun delete(itemId: Int)

    @Query("DELETE FROM phone_contacts")
    suspend fun deleteAll()

    @Query("SELECT * FROM phone_contacts WHERE id = :itemId")
    suspend fun get(itemId: Int): PhoneContact?

    @Query("SELECT * FROM phone_contacts WHERE id = :itemId")
    fun loadAt(itemId: Int): Flow<PhoneContact?>

    @Query("SELECT * FROM phone_contacts ORDER BY name ASC")
    fun load(): Flow<List<PhoneContact>>
}
