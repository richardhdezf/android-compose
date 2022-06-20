package com.example.app.data.dataSource.local

import com.example.app.data.PhoneContact
import com.example.app.data.dataSource.PhoneContactsDataSource
import kotlinx.coroutines.flow.Flow

class PhoneContactsLocalDataSource(private val phoneContactsDao: PhoneContactsDao) :
    PhoneContactsDataSource {
    override suspend fun insert(item: PhoneContact) = phoneContactsDao.insert(item)

    override suspend fun update(item: PhoneContact) = phoneContactsDao.update(item)

    override suspend fun delete(itemId: Int) = phoneContactsDao.delete(itemId)

    override suspend fun deleteAll() = phoneContactsDao.deleteAll()

    override suspend fun get(itemId: Int): PhoneContact? = phoneContactsDao.get(itemId)

    override fun loadAt(itemId: Int): Flow<PhoneContact?> = phoneContactsDao.loadAt(itemId)

    override fun load(): Flow<List<PhoneContact>> = phoneContactsDao.load()
}
