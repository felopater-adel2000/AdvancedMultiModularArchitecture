package com.multimodule.login.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimodule.login.domain.usecase.LoginUseCase
import com.multimodule.login.presentation.error.LoginError
import com.multimodule.login.presentation.protocol.LoginInput
import com.multimodule.login.presentation.protocol.LoginOutput
import com.multimodule.login.presentation.protocol.LoginViewState
import com.multimodule.login.presentation.validator.LoginValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    var loginViewState = LoginViewState()

    // output of viewmodel
    private val _viewOutPut: Channel<LoginOutput> = Channel()
    val viewOutput = _viewOutPut.receiveAsFlow()

    fun setInput(input: LoginInput) {
        when (input) {
            is LoginInput.LoginButtonClicked -> login()
            is LoginInput.PasswordUpdated -> updateState { copy(password = input.password) }
            is LoginInput.RegisterButtonClicked -> sendOutput { LoginOutput.NavigateToRegister }
            is LoginInput.UserNameUpdated -> updateState { copy(userName = input.username) }
        }
    }

    private fun updateState(updateState: LoginViewState.() -> LoginViewState) {
        loginViewState = loginViewState.updateState()
        validate()
    }

    private fun validate() {
        val userNameError: LoginError = LoginValidator.userNameError(loginViewState.userName)
        val passwordError: LoginError = LoginValidator.passwordError(loginViewState.password)
        val isLoginButtonEnabled: Boolean = LoginValidator.canDoLogin(userNameError, passwordError)

        loginViewState = loginViewState.copy(
            isLoginButtonEnabled = isLoginButtonEnabled,
            userNameError = userNameError,
            passwordError = passwordError,
        )
    }

    private fun sendOutput(action: () -> LoginOutput) {
        viewModelScope.launch {
            _viewOutPut.send(action())
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase.execute(
                LoginUseCase.Input(
                    username = loginViewState.userName,
                    password = loginViewState.password,
                ),
                success = {
                },
                error = {},
            )
        }
    }
}