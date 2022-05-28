package com.aregyan.compose.network

import com.aregyan.compose.network.model.NetworkUserDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsService {

    @GET("/users/{user}")
    suspend fun getUserDetails(@Path("user") user: String): NetworkUserDetails
}