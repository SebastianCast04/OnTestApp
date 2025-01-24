package data.repositories

import database.user.Client

interface ClientRepository {
    suspend fun getClients(): Result<List<Client>>
    suspend fun addClient(client: Client): Result<Unit>
    suspend fun deleteClient(client: Client): Result<Unit>
    //suspend fun addPhoneToClient(clientId: Int, phone: String): Result<Unit>
    //suspend fun getPhonesForClient(clientId: Int): Result<List<Phone>>
    suspend fun updateClient(client: Client): Result<Unit>
}