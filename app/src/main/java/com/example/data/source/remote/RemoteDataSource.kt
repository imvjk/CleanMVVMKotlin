package com.example.data.source.remote

import com.example.core.functional.Result
import com.example.core.interactor.productToModel
import com.example.data.model.ProductModel
import com.example.util.AppExecutors
import com.example.util.DoubleArgSingleton
import kotlinx.coroutines.withContext

/**
 * Implementation of the data source that interact with server.
 */
class RemoteDataSource(private val appExecutors: AppExecutors,
                       private val apiService: ApiService) : com.example.data.IDataSource.IRemoteDataSource {

    // singleton
    companion object : DoubleArgSingleton<RemoteDataSource, AppExecutors, ApiService>(::RemoteDataSource)

    override suspend fun getProductsFromRemote(): Result<List<ProductModel>> = withContext(appExecutors.networkContext) {
        val call = apiService.getProduct()
        try {
            with(call.execute()) {
                if (isSuccessful) {
                    val items = productToModel(body())
                    Result.Success(items.sortedWith(compareBy { it.distance }))
                } else {
                    Result.Error(RemoteDataNotFoundException())
                }
            }
        } catch (exception: Exception) {
            Result.Error(RemoteDataNotFoundException())
        }

    }
}
