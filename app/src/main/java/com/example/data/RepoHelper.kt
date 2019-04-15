package com.example.data

import com.example.data.source.remote.ApiService
import com.example.data.source.remote.RemoteDataSource
import com.example.util.AppExecutors
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repo helper to setup the Repository.
 */
object RepoHelper {

    private const val BASE_URL = "https://qc05n0gp78.execute-api.eu-central-1.amazonaws.com/prod/"

    fun provideRepository(): com.example.data.Repository {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)
        val appExecutors = AppExecutors()
        return Repository.getInstance(RemoteDataSource.getInstance(appExecutors, apiService))
    }
}
