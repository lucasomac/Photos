package br.edu.ifsp.scl.sdm.dummyproducts.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.sdm.dummyproducts.model.Product

class ProductAdapter(private val context: Context, private val productList: MutableList<Product>) :
    ArrayAdapter<Product>(context, R.layout.simple_expandable_list_item_1, productList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val productView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.simple_expandable_list_item_1, parent, false).apply {
                tag = ProductHolder(findViewById(R.id.text1))
            }
        (productView.tag as ProductHolder).productTitleView.text = productList[position].title
        return productView
    }

    private data class ProductHolder(val productTitleView: TextView)
}