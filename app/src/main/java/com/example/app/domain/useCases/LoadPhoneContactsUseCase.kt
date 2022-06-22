package com.example.app.domain.useCases

import com.example.app.data.PhoneContact
import com.example.app.domain.interfaces.PhoneContactsRepository
import kotlinx.coroutines.flow.Flow

class LoadPhoneContactsUseCase(
    private val phoneContactsRepository: PhoneContactsRepository
) {
    operator fun invoke(): Flow<List<PhoneContact>> = phoneContactsRepository.load()
}
