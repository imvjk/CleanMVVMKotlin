package com.example.data

import com.example.core.functional.Result
import com.example.data.model.ProductModel


/**
 * Main entry point for accessing data. All operations to get or save data are non blocking.
 */
interface IDataSource {

    /**
     * Interface to get data from remote server.
     */
    interface IRemoteDataSource {
        suspend fun getProductsFromRemote(): Result<List<ProductModel>>
    }
}