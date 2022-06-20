package com.example.app.presentation

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.app.domain.PhoneContactsRepository

@Suppress("UNCHECKED_CAST")
class PhoneContactsDefaultViewModelFactory constructor(
    private val phoneContactsRepository: PhoneContactsRepository,
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
                PhoneContactsViewModel(phoneContactsRepository)
            isAssignableFrom(PhoneContactViewModel::class.java) ->
                PhoneContactViewModel(phoneContactsRepository, handle)
            isAssignableFrom(AddEditPhoneContactViewModel::class.java) ->
                AddEditPhoneContactViewModel(phoneContactsRepository, handle)
            else ->
                throw IllegalArgumentException("Unknown PhoneContactViewModel class: ${modelClass.name}")
        }
    } as T
}
