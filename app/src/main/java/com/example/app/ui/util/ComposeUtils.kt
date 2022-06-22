package com.example.app.ui.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.app.di.AppContainer
import com.example.app.presentation.PhoneContactsViewModelFactory

@Composable
fun getPhoneContactsViewModelFactory(
    appContainer: AppContainer,
    defaultArgs: Bundle? = null
): PhoneContactsViewModelFactory {
    val factoryArgs = appContainer.phoneContactsFactoryArgs
    return PhoneContactsViewModelFactory(
        factoryArgs,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}
