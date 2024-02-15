package br.edu.ifsp.scl.sdm.dummyproducts.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.dummyproducts.databinding.TileProductImageBinding

class ProductImageAdapter(val context: Context, private val productImageList: MutableList<Bitmap>) :
    RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {
    inner class ProductImageViewHolder(tpib: TileProductImageBinding) :
        RecyclerView.ViewHolder(tpib.productIv) {
        val productIv = tpib.productIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder =
        ProductImageViewHolder(
            (TileProductImageBinding.inflate(
                LayoutInflater.from(
                    context
                ), parent, false
            ))
        )

    override fun getItemCount(): Int = productImageList.size

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) =
        holder.productIv.setImageBitmap(productImageList[position])
}