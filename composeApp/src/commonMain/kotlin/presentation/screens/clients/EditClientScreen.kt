package presentation.screens.clients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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

class EditClientScreen(val client: Client): Screen {

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
        EditClientContent(
            client = client,
            clientViewModel
        )
    }

}

@Composable
fun EditClientContent(
    client: Client,
    viewModel: ClientViewModel
){
    var name by remember { mutableStateOf(client.name) }
    var email by remember { mutableStateOf(client.email) }
    val navigator: Navigator? = LocalNavigator.current

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
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.updateClient(client.copy(name = name, email = email))
                navigator?.pop()
            }) {
                Text("Guardar Cambios")
            }
        }
    }

}