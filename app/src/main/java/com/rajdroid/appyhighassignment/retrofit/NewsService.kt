package com.rajdroid.appyhighassignment.retrofit

import com.rajdroid.appyhighassignment.entites.NewsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val API_KEY="fdc8e9b04d2e418094b7644a116bc555"
interface NewsService {

    @Headers("x-api-key:$API_KEY")
    @GET("everything")
    suspend fun getDataFromAPI(
        @Query("q") query: String?
    ): Response<NewsList>
}