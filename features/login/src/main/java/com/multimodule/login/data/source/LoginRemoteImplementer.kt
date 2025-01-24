package com.multimodule.login.data.source

import com.multimodule.data.mapper.toDomain
import com.multimodule.domain.result.OutCome
import com.multimodule.data.source.NetworkDataSource
import com.multimodule.domain.model.User
import com.multimodule.login.data.requests.LoginRequestBody
import com.multimodule.login.data.servise.LoginService
import com.multimodule.login.data.mapper.LoginMapper

class LoginRemoteImplementer(
    private val networkDataSource: NetworkDataSource<LoginService>,
    private val loginMapper: LoginMapper,
) :
    LoginRemote {
    override suspend fun login(username: String, password: String): OutCome<User> {
        return networkDataSource.performRequest(
            request = {
                login(
                    LoginRequestBody(
                        username = username,
                        password = password
                    )
                ).await()
            },
            onSuccess = { response, _ -> OutCome.success(loginMapper.toDomain(response)) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) },
        )
    }
}