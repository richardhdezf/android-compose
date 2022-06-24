package com.example.app.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.useCases.DeletePhoneContactUseCase
import com.example.app.domain.useCases.LoadPhoneContactUseCase
import com.example.app.ui.PhoneContactsDestinationsArgs
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class PhoneContactUiState(
    val id: Int? = null,
    val name: String = "",
    val phone: String = "",
    val isFavorite: Boolean = false,
    val isLoading: Boolean = true
)

class PhoneContactViewModel(
    private val deletePhoneContactUseCase: DeletePhoneContactUseCase,
    private val loadPhoneContactUseCase: LoadPhoneContactUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val itemId: Int =
        savedStateHandle[PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG]!!

    fun delete() {
        viewModelScope.launch {
            deletePhoneContactUseCase(itemId)
        }
    }

    fun loadAt(): Flow<PhoneContactUiState> =
        loadPhoneContactUseCase(itemId)
            .map {
                if (it == null) PhoneContactUiState() else PhoneContactUiState(
                    it.id,
                    it.name,
                    it.phone,
                    it.isFavorite,
                    isLoading = false
                )
            }
}
