package com.multimodule.login.data.source

import com.multimodule.data.result.OutCome
import com.multimodule.login.data.requests.LoginRequestBody
import com.multimodule.login.domain.model.User

interface LoginRemote {
    suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User>
}