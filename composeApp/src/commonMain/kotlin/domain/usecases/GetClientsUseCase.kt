package domain.usecases

import data.repositories.ClientRepository
import database.user.Client

class GetClientsUseCase(private val repository: ClientRepository) {
    suspend operator fun invoke(): Result<List<Client>> = repository.getClients()
}