package com.multimodule.data.result

interface UseCase<R> {

    suspend fun onSuccess(success: OutCome.Success<R>)

    suspend fun onEmpty()

    suspend fun onError(errorMessage: ErrorMessage)
}