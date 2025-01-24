package presentation.screens.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.usecases.LoginUseCase
import domain.util.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ScreenModel {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    fun login(email: String, password: String) {
        screenModelScope.launch {
            val result = loginUseCase(email, password)
            _loginState.value = when {
                result.isSuccess -> LoginState.Success
                else -> LoginState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }
}