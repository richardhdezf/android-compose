package com.example.app.ui.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.app.di.AppContainer
import com.example.app.presentation.PhoneContactsDefaultViewModelFactory

@Composable
fun getPhoneContactsViewModelFactory(
    appContainer: AppContainer,
    defaultArgs: Bundle? = null
): PhoneContactsDefaultViewModelFactory {
    val repository = appContainer.phoneContactsRepository
    return PhoneContactsDefaultViewModelFactory(
        repository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}
