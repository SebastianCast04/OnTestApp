package domain.usecases

import data.repositories.ClientRepository
import database.user.Client

class AddClientUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(client: Client): Result<Unit> = repository.addClient(client)
}