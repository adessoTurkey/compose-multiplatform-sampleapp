package com.example.moveeapp_compose_kmm.domain.account

class GetAccountDetailUseCase(
    private val repository: AccountRepository
) {

    suspend fun execute(): Result<AccountDetail> {
        return repository.getAccountDetail()
    }
}
