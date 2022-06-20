package com.example.app.di

import android.content.Context
import com.example.app.data.PhoneContactsDefaultRepository
import com.example.app.data.PhoneContactsRepository
import com.example.app.data.dataSource.local.ApplicationDatabase
import com.example.app.data.dataSource.local.PhoneContactsLocalDataSource

interface AppContainer {
    val phoneContactsRepository: PhoneContactsRepository
}

class DefaultAppContainer(private val applicationContext: Context) : AppContainer {

    override val phoneContactsRepository: PhoneContactsRepository by lazy {
        val database = ApplicationDatabase.getInstance(applicationContext)
        val localDataSource = PhoneContactsLocalDataSource(database.phoneContactsDao())
        PhoneContactsDefaultRepository(localDataSource)
    }
}
