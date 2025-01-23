package com.multimodule.login.data.source

import com.multimodule.domain.result.OutCome
import com.multimodule.login.data.requests.LoginRequestBody
import com.multimodule.login.domain.model.User

interface LoginRemote {
    suspend fun login( username: String, password: String): OutCome<User>
}