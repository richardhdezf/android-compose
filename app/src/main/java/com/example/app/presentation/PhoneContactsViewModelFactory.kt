package com.example.app.presentation

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.app.domain.useCases.*

@Suppress("UNCHECKED_CAST")
class PhoneContactsViewModelFactory constructor(
    private val factoryArgs: PhoneContactsDefaultViewModelFactoryArgs,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(PhoneContactsViewModel::class.java) ->
                PhoneContactsViewModel(factoryArgs.loadPhoneContactsUseCase)
            isAssignableFrom(PhoneContactViewModel::class.java) ->
                PhoneContactViewModel(
                    factoryArgs.deletePhoneContactUseCase,
                    factoryArgs.loadPhoneContactUseCase,
                    handle
                )
            isAssignableFrom(AddEditPhoneContactViewModel::class.java) ->
                AddEditPhoneContactViewModel(
                    factoryArgs.savePhoneContactUseCase,
                    factoryArgs.getPhoneContactUseCase,
                    handle
                )
            else ->
                throw IllegalArgumentException("Unknown PhoneContactViewModel class: ${modelClass.name}")
        }
    } as T
}

data class PhoneContactsDefaultViewModelFactoryArgs(
    val deletePhoneContactUseCase: DeletePhoneContactUseCase,
    val savePhoneContactUseCase: SavePhoneContactUseCase,
    val getPhoneContactUseCase: GetPhoneContactUseCase,
    val loadPhoneContactUseCase: LoadPhoneContactUseCase,
    val loadPhoneContactsUseCase: LoadPhoneContactsUseCase,
)
