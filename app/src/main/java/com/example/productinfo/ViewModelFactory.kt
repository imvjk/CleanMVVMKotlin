package com.example.productinfo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.Repository
import com.example.productinfo.product.ProductsViewModel
import com.example.util.DoubleArgSingleton

/**
 * Factory class to create View Models.
 */
class ViewModelFactory private constructor(
        private val application: Application,
        private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    companion object : DoubleArgSingleton<ViewModelFactory, Application, Repository>(::ViewModelFactory)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(ProductsViewModel::class.java) ->
                        ProductsViewModel(application, repository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}
