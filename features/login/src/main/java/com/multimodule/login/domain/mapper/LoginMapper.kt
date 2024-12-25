package com.multimodule.login.domain.mapper

import com.multimodule.login.data.responses.UserResponse
import com.multimodule.login.domain.model.User

interface LoginMapper {
    suspend fun toDomain(userResponse: UserResponse): User
}