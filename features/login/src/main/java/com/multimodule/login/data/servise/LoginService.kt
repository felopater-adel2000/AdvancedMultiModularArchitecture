package com.multimodule.login.data.servise

import com.multimodule.login.data.requests.LoginRequestBody
import com.multimodule.login.data.responses.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://api.mockfly.dev/mocks/98322c65-d004-4282-8967-3353907e2c39"
const val EMAIL = "email"

interface LoginService {

    @POST("$BASE_URL/Auth/Login")
    fun login(
        @Body loginRequestBody: LoginRequestBody
    ): Deferred<Response<UserResponse>>


    @POST("$BASE_URL/Auth/ForgetPassword")
    fun forgetPassword(
        @Query(EMAIL) email: String
    ): Deferred<Response<Unit>>
}