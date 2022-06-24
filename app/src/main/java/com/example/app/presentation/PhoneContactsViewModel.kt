package com.example.app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.PhoneContact
import com.example.app.domain.useCases.LoadPhoneContactsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class PhoneContactsUiState(
    val items: List<PhoneContact> = emptyList()
)

class PhoneContactsViewModel(private val loadPhoneContactsUseCase: LoadPhoneContactsUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(PhoneContactsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadPhoneContactsUseCase().collect { itemList ->
                Log.d("error app", "error 1")
                _uiState.update { it.copy(items = itemList) }
            }
        }
    }
}
