package com.example.app.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.PhoneContactsRepository
import com.example.app.ui.PhoneContactsDestinationsArgs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class PhoneContactUiState(
    val id: Int? = null,
    val name: String = "",
    val phone: String = "",
    val isLoading: Boolean = true
)

class PhoneContactViewModel(
    private val phoneContactsRepository: PhoneContactsRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val itemId: Int =
        savedStateHandle[PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG]!!

    fun delete() {
        viewModelScope.launch {
            phoneContactsRepository.delete(itemId)
        }
    }

    fun loadItem(): Flow<PhoneContactUiState> =
        phoneContactsRepository.loadAt(itemId)
            .map {
                if (it == null) PhoneContactUiState() else PhoneContactUiState(
                    it.id,
                    it.name,
                    it.phone,
                    isLoading = false
                )
            }
}
