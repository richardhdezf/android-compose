package com.example.app.ui.addEditPhoneContact

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PhoneContact
import com.example.app.data.PhoneContactsRepository
import com.example.app.ui.PhoneContactsDestinationsArgs
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AddEditPhoneContactUiState(
    val id: Int? = null,
    val name: String = "",
    val phone: String = "",
)

class AddEditPhoneContactViewModel(
    private val phoneContactsRepository: PhoneContactsRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private var itemId: Int? = null
    private val _uiState = MutableStateFlow(AddEditPhoneContactUiState())

    init {
        val rawItemId: String? =
            savedStateHandle[PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG]
        itemId = rawItemId?.toInt()
        if (itemId == null) {
            _uiState.update { AddEditPhoneContactUiState() }
        } else {
            viewModelScope.launch {
                val item = phoneContactsRepository.get(itemId!!)
                _uiState.update {
                    if (item == null) AddEditPhoneContactUiState()
                    else it.copy(
                        id = item.id,
                        name = item.name,
                        phone = item.phone
                    )
                }
            }
        }
    }

    fun updateName(value: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(name = value)
        }
    }

    fun updatePhone(value: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(phone = value)
        }
    }

    fun save() {
        val itemExists = itemId != null
        val updatedItem =
            PhoneContact(id = itemId, name = _uiState.value.name, phone = _uiState.value.phone)
        viewModelScope.launch {
            if (itemExists) phoneContactsRepository.update(updatedItem)
            else phoneContactsRepository.insert(updatedItem)
        }
    }

    fun getItem(): StateFlow<AddEditPhoneContactUiState> = _uiState.asStateFlow()
}
