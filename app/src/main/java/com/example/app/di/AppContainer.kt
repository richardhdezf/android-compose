package com.example.app.di

import android.content.Context
import com.example.app.data.PhoneContactsDefaultRepository
import com.example.app.data.dataSource.local.ApplicationDatabase
import com.example.app.data.dataSource.local.PhoneContactsLocalDataSource
import com.example.app.domain.interfaces.PhoneContactsRepository
import com.example.app.domain.useCases.*
import com.example.app.presentation.PhoneContactsDefaultViewModelFactoryArgs

interface AppContainer {
    val phoneContactsFactoryArgs: PhoneContactsDefaultViewModelFactoryArgs
}

class DefaultAppContainer(private val applicationContext: Context) : AppContainer {

    override val phoneContactsFactoryArgs: PhoneContactsDefaultViewModelFactoryArgs by lazy {
        PhoneContactsDefaultViewModelFactoryArgs(
            deletePhoneContactUseCase = DeletePhoneContactUseCase(phoneContactsRepository),
            savePhoneContactUseCase = SavePhoneContactUseCase(phoneContactsRepository),
            getPhoneContactUseCase = GetPhoneContactUseCase(phoneContactsRepository),
            loadPhoneContactUseCase = LoadPhoneContactUseCase(phoneContactsRepository),
            loadPhoneContactsUseCase = LoadPhoneContactsUseCase(phoneContactsRepository)
        )
    }

    private val phoneContactsRepository: PhoneContactsRepository by lazy {
        val database = ApplicationDatabase.getInstance(applicationContext)
        val localDataSource = PhoneContactsLocalDataSource(database.phoneContactsDao())
        PhoneContactsDefaultRepository(localDataSource)
    }
}
