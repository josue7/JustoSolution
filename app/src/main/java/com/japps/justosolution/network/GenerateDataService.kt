package com.japps.justosolution.network

import com.japps.justosolution.model.GenerateData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import java.net.URL

interface GenerateDataService {
    
    @GET
    suspend fun getDataRandom(@Url url: String): Response<GenerateData>
}