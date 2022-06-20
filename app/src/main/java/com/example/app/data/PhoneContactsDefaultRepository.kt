package com.example.app.data

import com.example.app.data.dataSource.PhoneContactsDataSource
import kotlinx.coroutines.flow.Flow

class PhoneContactsDefaultRepository(private val localDataSource: PhoneContactsDataSource) :
    PhoneContactsRepository {
    override suspend fun insert(item: PhoneContact) = localDataSource.insert(item)

    override suspend fun update(item: PhoneContact) = localDataSource.update(item)

    override suspend fun delete(itemId: Int) = localDataSource.delete(itemId)

    override suspend fun deleteAll() = localDataSource.deleteAll()

    override suspend fun get(itemId: Int): PhoneContact? = localDataSource.get(itemId)

    override fun loadAt(itemId: Int): Flow<PhoneContact?> = localDataSource.loadAt(itemId)

    override fun load(): Flow<List<PhoneContact>> = localDataSource.load()
}
