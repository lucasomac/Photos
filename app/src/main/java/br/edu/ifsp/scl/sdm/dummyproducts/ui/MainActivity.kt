package br.edu.ifsp.scl.sdm.dummyproducts.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.dummyproducts.R
import br.edu.ifsp.scl.sdm.dummyproducts.adapter.PhotoAdapter
import br.edu.ifsp.scl.sdm.dummyproducts.databinding.ActivityMainBinding
import br.edu.ifsp.scl.sdm.dummyproducts.model.PhotoList
import br.edu.ifsp.scl.sdm.dummyproducts.model.PhotosJSONAPI
import com.android.volley.toolbox.ImageRequest

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val photoList: PhotoList = PhotoList()
    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter(this, photoList)
    }
    private var photoImage: Bitmap? = null
    private var photoThumbnail: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.mainTb.apply {
            title = getString(R.string.app_name)
        })

        amb.photoSp.apply {
            adapter = photoAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    retrievePhotoImages(photoList[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //NSA
                }

            }
        }
        amb.apply {
            photoImage.setImageBitmap(this@MainActivity.photoImage)
            photoThumbnail.setImageBitmap(this@MainActivity.photoThumbnail)
        }
        retrievePhotos()
    }

    private fun retrievePhotoImages(photo: PhotoList.Photo) {
        ImageRequest(photo.url, {
            this@MainActivity.photoImage = it
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, {
            Toast.makeText(
                this, getString(R.string.message_request_problem), Toast.LENGTH_SHORT
            ).show()
        }).also { PhotosJSONAPI.getInstance(this).addToRequestQueue(it) }
        ImageRequest(photo.thumbnailUrl, {
            this@MainActivity.photoThumbnail = it
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, {
            Toast.makeText(
                this, getString(R.string.message_request_problem), Toast.LENGTH_SHORT
            ).show()
        }).also { PhotosJSONAPI.getInstance(this).addToRequestQueue(it) }
    }


    private fun retrievePhotos() = PhotosJSONAPI.PhotoListRequest({
        it.also {
            photoAdapter.addAll(it)
        }
    }, {
        Toast.makeText(
            this, getString(R.string.message_request_problem), Toast.LENGTH_SHORT
        ).show()
    }).also {
        PhotosJSONAPI.getInstance(this).addToRequestQueue(it)
    }
}