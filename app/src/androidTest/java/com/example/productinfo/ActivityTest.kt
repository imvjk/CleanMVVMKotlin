package com.example.productinfo

import com.example.AcceptanceTest
import com.example.productinfo.product.ProductsActivity
import org.junit.Test

class ActivityTest  : AcceptanceTest<ProductsActivity>(ProductsActivity::class.java) {

    /**
     * Test if screen that shows result is visible.
     */
    @Test
    fun testIfScreenIsLoaded() {
        checkThat.viewIsVisibleAndContainsText(R.string.app_name)
    }
}