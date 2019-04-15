package com.example.productinfo.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.functional.Result
import com.example.core.interactor.launchSilent
import com.example.data.model.ProductModel
import java.util.*
import kotlin.concurrent.fixedRateTimer

/**
 * ViewModel to load data for [ProductsFragment].
 */
class ProductsViewModel(
        context: Application,
        private val repository: com.example.data.Repository
) : AndroidViewModel(context) {

    val dataLoading = MutableLiveData(false)
    val items: MutableLiveData<List<ProductModel>> = MutableLiveData(arrayListOf())
    val openNextEvent = MutableLiveData<ProductModel>()
    val noDataEvent = MutableLiveData(false)
    private lateinit var timer: Timer

    fun loadDataWithIntervalUpdate() {
        timer = fixedRateTimer(name = "intervalUpdate", initialDelay = 0, period = 10000) {
            loadData()
        }
    }

    private fun loadData() = launchSilent {
        dataLoading.value = true
        val result = repository.getProductsFromRemote()
        if (result is Result.Success) {
            items.value = result.data
            noDataEvent.value = items.value.isNullOrEmpty()
        } else {
            noDataEvent.value = true
        }
        dataLoading.value = false
    }

    fun openNext(productModel: ProductModel) {
        openNextEvent.value = productModel
    }
}