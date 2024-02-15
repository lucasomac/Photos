package br.edu.ifsp.scl.sdm.dummyproducts.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.sdm.dummyproducts.model.PhotoList

class PhotoAdapter(private val context: Context, private val photoList: PhotoList) :
    ArrayAdapter<PhotoList.Photo>(context, R.layout.simple_expandable_list_item_1, photoList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val photoView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.simple_expandable_list_item_1, parent, false).apply {
                tag = PhotoHolder(findViewById(R.id.text1))
            }
        (photoView.tag as PhotoHolder).productTitleView.text = photoList[position].title
        return photoView
    }

    private data class PhotoHolder(val productTitleView: TextView)
}