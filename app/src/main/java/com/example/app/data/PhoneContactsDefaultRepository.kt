package com.example.app.data

import com.example.app.data.dataSource.PhoneContactsDataSource
import kotlinx.coroutines.flow.Flow

class PhoneContactsDefaultRepository(private val localDataSource: PhoneContactsDataSource) :
    PhoneContactsRepository {
    override suspend fun insert(item: PhoneContact) = localDataSource.insert(item)

    override suspend fun update(item: PhoneContact) = localDataSource.update(item)

    override suspend fun delete(item: PhoneContact) = localDataSource.delete(item)

    override suspend fun deleteAll() = localDataSource.deleteAll()

    override fun get(itemId: Int): Flow<PhoneContact> = localDataSource.get(itemId)

    override fun getAll(): Flow<List<PhoneContact>> = localDataSource.getAll()
}
