package com.example.app.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.app.data.PhoneContactsRepository
import com.example.app.ui.addEditPhoneContact.AddEditPhoneContactViewModel
import com.example.app.ui.phoneContact.PhoneContactViewModel
import com.example.app.ui.phoneContacts.PhoneContactsViewModel

@Suppress("UNCHECKED_CAST")
class PhoneContactsViewModelFactory constructor(
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
