package com.example.app.data.dataSource.local

import androidx.room.*
import com.example.app.data.PhoneContact
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneContactsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: PhoneContact)

    @Update
    suspend fun update(item: PhoneContact)

    @Delete
    suspend fun delete(item: PhoneContact)

    @Query("DELETE FROM phone_contacts")
    suspend fun deleteAll()

    @Query("SELECT * FROM phone_contacts WHERE id = :itemId")
    fun get(itemId: Int): Flow<PhoneContact>

    @Query("SELECT * FROM phone_contacts ORDER BY name ASC")
    fun getAll(): Flow<List<PhoneContact>>
}
