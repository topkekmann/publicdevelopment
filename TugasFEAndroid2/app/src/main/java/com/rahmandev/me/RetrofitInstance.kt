package com.rahmandev.me

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    // callback interface
    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onError(e: Throwable)
    }

    // fungsi fetch dengan callback
    fun getUsers(callback: ApiCallback<List<User>>) {
        val call = retrofit.create(ApiService::class.java).getUsersCall() // perlu GET versi Call, bukan suspend
        call.enqueue(object : retrofit2.Callback<List<User>> {
            override fun onResponse(
                call: retrofit2.Call<List<User>>,
                response: retrofit2.Response<List<User>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.v("/b/", "Success: conn")
//                    callback.onSuccess(response.body()!!)
                } else {
                    Log.v("/b/", "Failed: conn")
//                    callback.onError(Throwable("Response error: ${response.code()}"))
                }
            }

            override fun onFailure(call: retrofit2.Call<List<User>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }
}

//loadingme.visibility = View.GONE