package com.example.app.domain.interfaces

import com.example.app.data.PhoneContact
import kotlinx.coroutines.flow.Flow

interface PhoneContactsRepository {
    suspend fun insert(item: PhoneContact)

    suspend fun update(item: PhoneContact)

    suspend fun delete(itemId: Int)

    suspend fun deleteAll()

    suspend fun get(itemId: Int): PhoneContact?

    fun loadAt(itemId: Int): Flow<PhoneContact?>

    fun load(): Flow<List<PhoneContact>>
}
