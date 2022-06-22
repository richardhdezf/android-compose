package com.example.app.domain.useCases

import com.example.app.data.PhoneContact
import com.example.app.domain.interfaces.PhoneContactsRepository

class SavePhoneContactUseCase(
    private val phoneContactsRepository: PhoneContactsRepository
) {
    suspend operator fun invoke(itemId: Int?, name: String, phone: String) {
        val item = PhoneContact(id = itemId, name = name, phone = phone)
        if (itemId == null) phoneContactsRepository.insert(item)
        else phoneContactsRepository.update(item)
    }
}
