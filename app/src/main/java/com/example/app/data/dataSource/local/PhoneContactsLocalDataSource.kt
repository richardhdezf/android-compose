package com.example.app.data.dataSource.local

import com.example.app.data.PhoneContact
import com.example.app.data.dataSource.PhoneContactsDataSource
import kotlinx.coroutines.flow.Flow

class PhoneContactsLocalDataSource(private val phoneContactsDao: PhoneContactsDao) :
    PhoneContactsDataSource {
    override suspend fun insert(item: PhoneContact) = phoneContactsDao.insert(item)

    override suspend fun update(item: PhoneContact) = phoneContactsDao.update(item)

    override suspend fun delete(item: PhoneContact) = phoneContactsDao.delete(item)

    override suspend fun deleteAll() = phoneContactsDao.deleteAll()

    override fun get(itemId: Int): Flow<PhoneContact> = phoneContactsDao.get(itemId)

    override fun getAll(): Flow<List<PhoneContact>> = phoneContactsDao.getAll()
}
