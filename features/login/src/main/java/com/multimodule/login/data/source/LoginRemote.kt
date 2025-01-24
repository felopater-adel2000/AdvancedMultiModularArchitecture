package com.multimodule.login.data.source

import com.multimodule.domain.model.User
import com.multimodule.domain.result.OutCome
import com.multimodule.login.data.requests.LoginRequestBody

interface LoginRemote {
    suspend fun login( username: String, password: String): OutCome<User>
}