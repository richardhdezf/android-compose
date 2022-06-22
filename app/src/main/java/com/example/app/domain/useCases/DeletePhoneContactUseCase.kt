package com.example.app.domain.useCases

import com.example.app.domain.interfaces.PhoneContactsRepository

class DeletePhoneContactUseCase(
    private val phoneContactsRepository: PhoneContactsRepository
) {
    suspend operator fun invoke(itemId: Int) {
        phoneContactsRepository.delete(itemId)
    }
}
