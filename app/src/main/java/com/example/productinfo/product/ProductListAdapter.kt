package com.example.productinfo.product

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productinfo.R
import com.example.core.extension.inflate
import com.example.data.model.ProductModel
import kotlinx.android.synthetic.main.list_item.view.scooterDistance
import kotlinx.android.synthetic.main.list_item.view.scooterModel
import kotlin.properties.Delegates

/**
 * List adapter.
 */
class ProductListAdapter(
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var collection: List<ProductModel> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (ProductModel) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.list_item))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: ProductModel, clickListener: (ProductModel) -> Unit) {
            with(itemView) {
                scooterModel.text = model.model
                scooterDistance.text = model.distance
                setOnClickListener { clickListener(model) }
            }
        }
    }
}
