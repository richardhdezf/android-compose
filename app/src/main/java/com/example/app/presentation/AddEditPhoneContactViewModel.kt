package com.example.app.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.*
import com.example.app.domain.useCases.GetPhoneContactUseCase
import com.example.app.domain.useCases.SavePhoneContactUseCase
import com.example.app.ui.PhoneContactsDestinationsArgs
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AddEditPhoneContactUiState(
    val id: Int? = null,
    val name: String = "",
    val phone: String = "",
    val isEditing: Boolean = false,
)

class AddEditPhoneContactViewModel(
    private val savePhoneContactUseCase: SavePhoneContactUseCase,
    private val getPhoneContactUseCase: GetPhoneContactUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private var itemId: Int? = null
    private val _uiState = MutableStateFlow(AddEditPhoneContactUiState())

    init {
        val rawItemId: String? =
            savedStateHandle[PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG]
        itemId = rawItemId?.toInt()
        viewModelScope.launch {
            val result = getPhoneContactUseCase(itemId)
            _uiState.update {
                if (result.succeeded) {
                    val item = result.getData()
                    it.copy(
                        id = item.id,
                        name = item.name,
                        phone = item.phone,
                        isEditing = true
                    )
                } else {
                    AddEditPhoneContactUiState()
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
        viewModelScope.launch {
            savePhoneContactUseCase(itemId, _uiState.value.name, _uiState.value.phone)
        }
    }

    fun getItem(): StateFlow<AddEditPhoneContactUiState> = _uiState.asStateFlow()
}
