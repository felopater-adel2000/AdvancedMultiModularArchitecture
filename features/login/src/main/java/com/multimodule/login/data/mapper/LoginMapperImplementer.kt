package com.multimodule.login.data.mapper

import com.multimodule.domain.model.User
import com.multimodule.login.data.responses.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginMapperImplementer(private val defaultDispatcher: CoroutineDispatcher) : LoginMapper {
    override suspend fun toDomain(userResponse: UserResponse): User {
        return withContext(defaultDispatcher) {
            User(
                id = userResponse.id.orEmpty(),
                fullName = userResponse.fullName.orEmpty(),
                email = userResponse.email.orEmpty(),
                photo = userResponse.photo.orEmpty()
            )
        }
    }
}