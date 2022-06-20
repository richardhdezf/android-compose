package com.example.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PhoneContact
import com.example.app.domain.PhoneContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

data class PhoneContactsUiState(val items: List<PhoneContact>)

class PhoneContactsViewModel(private val phoneContactsRepository: PhoneContactsRepository) :
    ViewModel() {
    fun deleteAll() {
        viewModelScope.launch {
            phoneContactsRepository.deleteAll()
        }
    }

    fun getAll(): Flow<List<PhoneContact>> = phoneContactsRepository.load()
}
