package com.multimodule.login.domain.usecase

import com.multimodule.domain.result.OutCome
import com.multimodule.domain.usecase.AsyncUseCase
import com.multimodule.login.data.source.LoginRemote
import com.multimodule.login.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRemote: LoginRemote) :
    AsyncUseCase<LoginUseCase.Input, User>() {

    override suspend fun run(input: Input): OutCome<User> {
        return loginRemote.login(username = input.username, password = input.password)
    }

    data class Input(val username: String, val password: String)
}