package presentation.screens.clients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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

class AddClientScreen: Screen {
    @Composable
    override fun Content() {

        val clientViewModel = rememberScreenModel {
            ClientViewModel(
                getClientsUseCase = getKoin().get(),
                addClientUseCase = getKoin().get(),
                //addPhoneUseCase = getKoin().get(),
                clientRepository = getKoin().get(),
                deleteClientUseCase = getKoin().get(),
            )
        }
        val navigator: Navigator? = LocalNavigator.current


        AddClientContent(
            clientViewModel,
            onClientAdded = {
                navigator?.pop()
            }
        )
    }
}

@Composable
fun AddClientContent(
    clientViewModel: ClientViewModel,
    onClientAdded: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val onClientAddedSuccess: () -> Unit = {
        showDialog = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                clientViewModel.addClient(Client(name = name, email = email, phone = phone))
                onClientAddedSuccess()
                onClientAdded()
            }) {
                Text(text = "Guardar")
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Éxito") },
                text = { Text("Cliente agregado exitosamente.") },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorScheme.inverseSurface)
                    ) {
                        Text("Aceptar", color = colorScheme.primary)
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        }
    }
}
