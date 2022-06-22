package com.example.app.domain.useCases

import com.example.app.data.PhoneContact
import com.example.app.data.PhoneContactException
import com.example.app.data.Result
import com.example.app.domain.interfaces.PhoneContactsRepository

class GetPhoneContactUseCase(
    private val phoneContactsRepository: PhoneContactsRepository
) {
    suspend operator fun invoke(itemId: Int?): Result<PhoneContact> {
        if (itemId == null) return Result.Error(PhoneContactException("ItemId $itemId is null"))

        val item = phoneContactsRepository.get(itemId)
        return if (item == null) Result.Error(PhoneContactException("Item $itemId not found"))
        else Result.Success(item)
    }
}
