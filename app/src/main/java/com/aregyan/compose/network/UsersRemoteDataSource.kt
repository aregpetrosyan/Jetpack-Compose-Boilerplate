package com.aregyan.compose.network

import com.aregyan.compose.network.model.NetworkUserListItem
import retrofit2.http.GET

interface UsersRemoteDataSource {

    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUsers(): List<NetworkUserListItem>
}