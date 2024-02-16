package br.edu.ifsp.scl.sdm.dummyproducts.model

import java.util.Locale

class PhotoList : ArrayList<PhotoList.Photo>() {
    inner class Photo(
        val albumId: Int, val id: Int, val thumbnailUrl: String, val title: String, val url: String
    ) {
        override fun toString(): String {
            return titleCapitalized()
        }

        fun titleCapitalized(): String {
            return title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
    }
}