package com.example.app.ui

import androidx.lifecycle.*
import com.example.app.data.PhoneContact
import com.example.app.data.PhoneContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map

data class AddEditPhoneContactInput(
    val id: Int? = null,
    val name: String = "",
    val phone: String = "",
)

class PhoneContactsViewModel(private val phoneContactsRepository: PhoneContactsRepository) :
    ViewModel() {
    fun save(itemId: Int?, name: String, phone: String) {
        val itemExists = itemId != null
        val updatedItem = PhoneContact(id = itemId, name = name, phone = phone)
        viewModelScope.launch {
            if (itemExists) phoneContactsRepository.update(updatedItem)
            else phoneContactsRepository.insert(updatedItem)
        }
    }

    fun delete(item: PhoneContact) {
        viewModelScope.launch {
            phoneContactsRepository.delete(item)
        }
    }

/*
    fun deleteAll() {
        viewModelScope.launch {
            phoneContactsRepository.deleteAll()
        }
    }
*/

    fun getItem(itemId: Int): Flow<PhoneContact?> = phoneContactsRepository.get(itemId)

    fun getEditableItem(itemId: Int?): Flow<AddEditPhoneContactInput> =
        if (itemId == null) flow {
            emit(AddEditPhoneContactInput()) // Emits the result of the request to the flow
        } else phoneContactsRepository.get(itemId)
            .map { AddEditPhoneContactInput(it.id, it.name, it.phone) }

    fun getAll(): Flow<List<PhoneContact>> = phoneContactsRepository.getAll()
}
