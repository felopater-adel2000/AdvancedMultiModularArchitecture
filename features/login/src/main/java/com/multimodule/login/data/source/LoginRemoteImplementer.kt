package com.multimodule.login.data.source

import com.multimodule.data.error.toDomain
import com.multimodule.data.result.OutCome
import com.multimodule.data.source.NetworkDataSource
import com.multimodule.login.data.requests.LoginRequestBody
import com.multimodule.login.data.servise.LoginService
import com.multimodule.login.domain.mapper.LoginMapper
import com.multimodule.login.domain.model.User

class LoginRemoteImplementer(
    private val networkDataSource: NetworkDataSource<LoginService>,
    private val loginMapper: LoginMapper
) :
    LoginRemote {
    override suspend fun login(loginRequestBody: LoginRequestBody): OutCome<User> {
        return networkDataSource.performRequest(
            request = { login(loginRequestBody).await() },
            onSuccess = { response, _ -> OutCome.success(loginMapper.toDomain(response)) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
    }
}