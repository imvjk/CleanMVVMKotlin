package com.example.data

import com.example.core.functional.Result
import com.example.data.model.ProductModel
import com.example.data.source.remote.RemoteDataNotFoundException
import com.example.data.source.remote.RemoteDataSource
import com.example.util.SingleArgSingleton

/**
 * Implementation of IDataSource. work like a wrapper for all data source ( local or remote).
 */
class Repository private constructor(private val remoteIDataSource: RemoteDataSource) : IDataSource,
        IDataSource.IRemoteDataSource {

    companion object : SingleArgSingleton<Repository, RemoteDataSource>(::Repository)

    override suspend fun getProductsFromRemote(): Result<List<ProductModel>> {
        val remoteResult = remoteIDataSource.getProductsFromRemote()
        return when (remoteResult) {
            is Result.Success -> {
                Result.Success(remoteResult.data)
            }
            is Result.Error -> {
                Result.Error(RemoteDataNotFoundException())
            }
        }
    }
}
