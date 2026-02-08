package com.armandorochin.borderwaittimes.data.remote.bwt

import com.armandorochin.borderwaittimes.data.remote.bwt.dtos.BorderWaitTime
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BwtRepository @Inject constructor(
    private val bwtApi: BwtApi
) {

    suspend fun fetchBwtFromServer() : Result<BorderWaitTime> = withContext(IO) {
        try {
            val response = bwtApi.getBwtDataFromServer().execute()

            when{
                !response.isSuccessful -> {
                    Result.failure(Exception("Response was not successful. Code: ${response.code()}"))
                }
                response.body() == null -> {
                    Result.failure(Exception("Response body is null."))
                }
                else -> {
                    Result.success(response.body()!!)
                }
            }
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }
}
