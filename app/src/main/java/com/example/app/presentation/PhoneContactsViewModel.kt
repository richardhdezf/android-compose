package com.example.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PhoneContact
import com.example.app.domain.useCases.LoadPhoneContactsUseCase
import kotlinx.coroutines.flow.*

data class PhoneContactsUiState(val items: List<PhoneContact> = emptyList())

class PhoneContactsViewModel(private val loadPhoneContactsUseCase: LoadPhoneContactsUseCase) :
    ViewModel() {
    fun load(): Flow<PhoneContactsUiState> =
        loadPhoneContactsUseCase().map { PhoneContactsUiState(items = it) }
}
