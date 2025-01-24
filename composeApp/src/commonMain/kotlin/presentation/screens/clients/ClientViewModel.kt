package presentation.screens.clients

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.repositories.ClientRepository
import database.user.Client
import domain.usecases.AddClientUseCase
import domain.usecases.DeleteClientUseCase
import domain.usecases.GetClientsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel(
    private val getClientsUseCase: GetClientsUseCase,
    private val addClientUseCase: AddClientUseCase,
    private val deleteClientUseCase: DeleteClientUseCase,
    //private val addPhoneUseCase: AddPhoneUseCase,
    private val clientRepository: ClientRepository

) : ScreenModel {

    private val _clientsState = MutableStateFlow<List<Client>>(emptyList())
    val clientsState: StateFlow<List<Client>> get() = _clientsState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState

    /*private val _phonesState = MutableStateFlow<List<Phone>>(emptyList())
    val phonesState: StateFlow<List<Phone>> get() = _phonesState

     */

    fun loadClients() {
        screenModelScope.launch {
            getClientsUseCase().onSuccess {
                _clientsState.value = it
            }.onFailure {
                _errorState.value = it.message
            }
        }
    }

    fun addClient(client: Client) {
        screenModelScope.launch {
            addClientUseCase(client).onFailure {
                _errorState.value = it.message
            }.also {
                loadClients()
            }
        }
    }

    fun deleteClient(client: Client) {
        screenModelScope.launch {
            deleteClientUseCase(client).onFailure {
                _errorState.value = it.message
            }.also {
                loadClients()
            }
        }
    }

   /* fun addPhone(clientId: Int, phone: String) {
        screenModelScope.launch {
            addPhoneUseCase(clientId, phone).onFailure {
                _errorState.value = it.message
            }.also {
                loadPhones(clientId)
            }
        }
    }

    fun loadPhones(clientId: Int) {
        screenModelScope.launch {
            clientRepository.getPhonesForClient(clientId).onSuccess {
                _phonesState.value = it
            }.onFailure {
                _errorState.value = it.message
            }
        }
    }

    */

    fun updateClient(client: Client) {
        screenModelScope.launch {
            clientRepository.updateClient(client).onFailure {
            }.onSuccess {
                loadClients()
            }
        }
    }
}
