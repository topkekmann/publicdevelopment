package com.rahmandev.me

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    // callback style
    @GET("users")
    fun getUsersCall(): Call<List<User>>
}