package presentation.screens.clients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import database.user.Client
import org.koin.mp.KoinPlatform.getKoin
import presentation.Theme.colorScheme
import presentation.screens.login.LoginViewModel

class ClientListScreen : Screen {
    @Composable
    override fun Content() {
        val clientViewModel = rememberScreenModel {
            ClientViewModel(
                getClientsUseCase = getKoin().get(),
                addClientUseCase = getKoin().get(),
                clientRepository = getKoin().get(),
                deleteClientUseCase = getKoin().get(),
                //addPhoneUseCase = getKoin().get(),
            )
        }
        val navigator: Navigator? = LocalNavigator.current

        ClientListContent(
            clientViewModel,
            onAddClientClicked = {
                navigator?.push(AddClientScreen())
            },
            onEditClientClicked = { client ->
                navigator?.push(EditClientScreen(client))
            }
        )
    }
}

@Composable
fun ClientListContent(
    viewModel: ClientViewModel,
    onAddClientClicked: () -> Unit,
    onEditClientClicked: (Client) -> Unit
) {
    val clients by viewModel.clientsState.collectAsState()
    val error by viewModel.errorState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadClients()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (error != null) {
                Text(text = "Error: $error", color = Color.Red)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(clients) { client ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${client.name} - ${client.email}")
                        Row {
                            IconButton(onClick = { onEditClientClicked(client) }) {
                                Icon(Icons.Filled.Edit, contentDescription = "Editar")
                            }
                            IconButton(onClick = {
                                viewModel.deleteClient(client)
                                showDeleteDialog = true
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }

            Button(onClick = { onAddClientClicked() }) {
                Text(text = "Agregar Cliente")
            }
        }

        if (showDeleteDialog) {
            androidx.compose.material.AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { androidx.compose.material.Text(text = "Éxito") },
                text = { androidx.compose.material.Text("Cliente eliminado exitosamente.") },
                confirmButton = {
                    androidx.compose.material.Button(
                        onClick = { showDeleteDialog = false },
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = colorScheme.inverseSurface
                        )
                    ) {
                        androidx.compose.material.Text("Aceptar", color = colorScheme.primary)
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        }
    }
}