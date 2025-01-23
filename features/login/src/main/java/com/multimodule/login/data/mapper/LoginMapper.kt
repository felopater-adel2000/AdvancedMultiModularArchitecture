package com.multimodule.login.data.mapper

import com.multimodule.login.data.responses.UserResponse
import com.multimodule.login.domain.model.User

interface LoginMapper {
    suspend fun toDomain(userResponse: UserResponse): User
}