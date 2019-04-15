package com.example.productinfo.product

import android.app.Application
import com.example.AndroidTest
import com.example.UnitTest
import com.example.core.functional.Result
import com.example.data.Repository
import com.example.productinfo.product.ProductsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ProductViewModelTest : AndroidTest() {

    private lateinit var productViewModel: ProductsViewModel

    @Mock private lateinit var repository: Repository

    @Mock private lateinit var application: Application

    @Before
    fun setUp() {
        productViewModel = ProductsViewModel(application, repository)
    }

    @Test
    fun testDataLoadingAtInternal() {
        `when`(runBlocking {repository.getProductsFromRemote()}).thenReturn(Result.Success(arrayListOf()))
        with(productViewModel) {
            dataLoading.observeForever {
                assert(it.not())
            }
            items.observeForever {
                assert(it.isEmpty())
            }
        }
    }
}