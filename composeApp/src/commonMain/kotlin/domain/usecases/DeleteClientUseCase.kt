package domain.usecases

import data.repositories.ClientRepository
import database.user.Client

class DeleteClientUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(client: Client): Result<Unit> = repository.deleteClient(client)
}