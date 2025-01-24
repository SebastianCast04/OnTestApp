package data.repositories

import database.user.Client
import database.user.ClientDao


class ClientRepositoryImpl(private val clientDao: ClientDao, /*private val phoneDao: PhoneDao*/) : ClientRepository {
    override suspend fun getClients(): Result<List<Client>> = runCatching {
        clientDao.getAllClients()
    }

    override suspend fun addClient(client: Client): Result<Unit> = runCatching {
        clientDao.insertClient(client)
    }

    override suspend fun deleteClient(client: Client): Result<Unit> = runCatching {
        clientDao.deleteClient(client)
    }

    /*override suspend fun addPhoneToClient(clientId: Int, phone: String): Result<Unit> = runCatching {
        phoneDao.insertPhone(Phone(clientId = clientId, phoneNumber = phone))
    }

    override suspend fun getPhonesForClient(clientId: Int): Result<List<Phone>> = runCatching {
        phoneDao.getPhonesByClientId(clientId)
    }

     */

    override suspend fun updateClient(client: Client): Result<Unit> = runCatching {
        clientDao.insertClient(client)
    }
}