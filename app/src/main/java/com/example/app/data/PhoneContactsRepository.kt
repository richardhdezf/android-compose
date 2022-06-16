package com.example.app.data

import kotlinx.coroutines.flow.Flow

interface PhoneContactsRepository {
    suspend fun insert(item: PhoneContact)

    suspend fun update(item: PhoneContact)

    suspend fun delete(item: PhoneContact)

    suspend fun deleteAll()

    fun get(itemId: Int): Flow<PhoneContact>

    fun getAll(): Flow<List<PhoneContact>>
}
