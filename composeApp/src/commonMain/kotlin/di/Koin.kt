package di
import org.koin.core.module.Module
import org.koin.dsl.module
import com.russhwolf.settings.Settings
import data.repositories.ClientRepository
import data.repositories.ClientRepositoryImpl
import data.repositories.UserRepository
import data.repositories.UserRepositoryImpl
import database.user.UserDatabase
import domain.usecases.AddClientUseCase
import domain.usecases.DeleteClientUseCase
import domain.usecases.GetClientsUseCase
import domain.usecases.LoginUseCase
import presentation.screens.clients.ClientViewModel
import presentation.screens.login.LoginViewModel


val appModule: Module = module {
    single<Settings> { Settings() }
    single { Settings() }
    single { get<UserDatabase>().userDao() }
    single { get<UserDatabase>().clientDao() }
   // single { get<UserDatabase>().phoneDao() }
}

val repositoryModule: Module = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single <ClientRepository> { ClientRepositoryImpl (get()) }
}

val useCasesModule: Module = module {
    factory { LoginUseCase(get()) }
    factory { AddClientUseCase(get()) }
    factory { GetClientsUseCase(get()) }
    factory { DeleteClientUseCase(get()) }
    //factory { AddPhoneUseCase(get()) }
}

val viewModelModule: Module = module {
    factory {
        LoginViewModel(
            loginUseCase = get(),
        )
    }

    factory {
        ClientViewModel(
            addClientUseCase = get(),
            getClientsUseCase = get(),
            deleteClientUseCase = get(),
           // addPhoneUseCase = get(),
            clientRepository = get(),
        )
    }
}