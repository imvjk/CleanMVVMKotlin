package com.example.core.interactor

import com.example.core.entity.Product
import com.example.data.model.ProductModel

fun productToModel(product: Product) : List<ProductModel> {
    return product.data.scooters.map {
        ProductModel(
                it.model,
                it.distance_to_travel.toString(),
                "${it.location.lat} ${it.location.lat}")}
}