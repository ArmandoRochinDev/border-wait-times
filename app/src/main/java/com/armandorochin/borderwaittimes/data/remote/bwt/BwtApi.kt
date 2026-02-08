package com.armandorochin.borderwaittimes.data.remote.bwt

import com.armandorochin.borderwaittimes.data.remote.bwt.dtos.BorderWaitTime
import retrofit2.Call
import retrofit2.http.GET

interface BwtApi {

    @GET("xml/bwt.xml")
    fun getBwtDataFromServer() : Call<BorderWaitTime>
}