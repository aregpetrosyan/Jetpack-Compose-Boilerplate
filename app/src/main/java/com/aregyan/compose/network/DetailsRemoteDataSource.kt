package com.aregyan.compose.network

import com.aregyan.compose.network.model.NetworkUserDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsRemoteDataSource {

    @GET("/users/{user}")
    suspend fun getDetails(@Path("user") user: String): NetworkUserDetails
}