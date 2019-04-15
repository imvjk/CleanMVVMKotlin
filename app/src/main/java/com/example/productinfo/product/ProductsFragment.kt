package com.example.productinfo.product

import android.os.Bundle
import android.view.View
import com.example.productinfo.R
import com.example.core.extension.getViewModel
import com.example.core.extension.observe
import com.example.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.emptyView
import kotlinx.android.synthetic.main.fragment_list.recyclerView

/**
 * Fragment class to display products list.
 */
class ProductsFragment : BaseFragment() {

    private lateinit var listAdapter: ProductListAdapter
    private lateinit var productsViewModel: ProductsViewModel

    companion object {
        private const val TAG = "ProductsFragment"
        fun newInstance() = ProductsFragment()
    }

    override fun layoutId() = R.layout.fragment_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModel = getViewModel(ProductsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()

        with(productsViewModel) {
            observe(dataLoading) { value ->
                if (value) showProgress() else {
                    hideProgress()
                    println("Request completed.")
                }
            }
            observe(items) { value ->
               listAdapter.collection = value.orEmpty()
            }
            observe(noDataEvent) { value ->
                emptyView.visibility = if (value) {
                    listAdapter.collection = emptyList()
                    View.VISIBLE
                } else View.GONE
            }
        }

        productsViewModel.loadDataWithIntervalUpdate()
    }

    private fun setupListAdapter() {
        listAdapter = ProductListAdapter()
        with(recyclerView) {
            val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setLayoutManager(layoutManager)
            setHasFixedSize(true)
            adapter = listAdapter
        }
        listAdapter.clickListener = { productsViewModel.openNext(it) }
    }
}