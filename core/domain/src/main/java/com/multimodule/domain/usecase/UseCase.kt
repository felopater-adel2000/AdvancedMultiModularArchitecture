package com.multimodule.domain.usecase

import com.multimodule.domain.result.OutCome

interface UseCase<R> {

    suspend fun onSuccess(success: OutCome.Success<R>)

    suspend fun onEmpty()

    suspend fun onError(errorMessage: com.multimodule.domain.model.ErrorMessage)
}