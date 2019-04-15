package com.example.productinfo.product

import android.os.Bundle
import com.example.core.extension.getViewModel
import com.example.core.extension.observe
import com.example.core.extension.visible
import com.example.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_layout.dashboardView
import kotlinx.android.synthetic.main.activity_layout.dashboardViewBody
import kotlinx.android.synthetic.main.activity_layout.dashboardViewText

class ProductsActivity : BaseActivity() {

    override fun fragment() = ProductsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(getViewModel(ProductsViewModel::class.java)) {
            observe(openNextEvent) { value ->
                dashboardView.visible()
                dashboardViewText.text = value.model
                dashboardViewBody.text = value.location
            }
        }
    }
}
