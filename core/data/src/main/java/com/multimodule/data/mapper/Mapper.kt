package com.multimodule.data.mapper

import com.multimodule.data.response.ErrorResponse

// mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(code: Int): com.multimodule.domain.model.ErrorMessage {
    return com.multimodule.domain.model.ErrorMessage(
        code = code,
        message = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList()
    )
}