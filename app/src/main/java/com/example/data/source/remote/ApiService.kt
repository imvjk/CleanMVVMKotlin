package com.example.data.source.remote

import com.example.core.entity.Product
import retrofit2.Call
import retrofit2.http.GET

/**
 * Api call for retrofit.
 */
interface ApiService {

    @GET("scooters")
    fun getProduct(): Call<Product>

}