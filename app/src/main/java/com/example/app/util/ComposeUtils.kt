package com.example.app.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.app.di.AppContainer
import com.example.app.ui.PhoneContactsViewModelFactory

@Composable
fun getPhoneContactsViewModelFactory(
    appContainer: AppContainer,
    defaultArgs: Bundle? = null
): PhoneContactsViewModelFactory {
    val repository = appContainer.phoneContactsRepository
    return PhoneContactsViewModelFactory(
        repository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}
