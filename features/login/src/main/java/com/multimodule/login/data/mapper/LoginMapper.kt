package com.multimodule.login.data.mapper

import com.multimodule.domain.model.User
import com.multimodule.login.data.responses.UserResponse

interface LoginMapper {
    suspend fun toDomain(userResponse: UserResponse): User
}