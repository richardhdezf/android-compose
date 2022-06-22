package com.example.app.domain.useCases

import com.example.app.data.PhoneContact
import com.example.app.domain.interfaces.PhoneContactsRepository
import kotlinx.coroutines.flow.Flow

class LoadPhoneContactUseCase(
    private val phoneContactsRepository: PhoneContactsRepository
) {
    operator fun invoke(itemId: Int): Flow<PhoneContact?> = phoneContactsRepository.loadAt(itemId)
}
