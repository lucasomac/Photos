package br.edu.ifsp.scl.sdm.dummyproducts.model

class PhotoList : ArrayList<PhotoList.PhotoListItem>() {
    inner class PhotoListItem(
        val albumId: Int, val id: Int, val thumbnailUrl: String, val title: String, val url: String
    )
}