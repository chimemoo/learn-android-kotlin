package com.chimemoo.whatsanime.ui.main.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chimemoo.whatsanime.R
import com.chimemoo.whatsanime.data.api.ApiHelper
import com.chimemoo.whatsanime.data.api.ApiService
import com.chimemoo.whatsanime.ui.base.ViewModelFactory
import com.chimemoo.whatsanime.ui.main.viewmodel.MainViewModel
import com.chimemoo.whatsanime.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var mainVewModel: MainViewModel

    private val REQUEST_IMAGE_CAPTURE = 1
    private val MY_CAMERA_REQUEST_CODE = 100

    private val EXTRA_ANIME_ID ="EXTRA_ANIME_ID"

    private var imageData : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupObserver()
        setContentView(R.layout.activity_main)
        getPermition()
        setupUI()
    }

    private fun setupViewModel () {
        mainVewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiService()))
        ).get(MainViewModel::class.java)
    }

    private fun setupObserver() {
        mainVewModel.postImage().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    pb_upload.visibility = View.INVISIBLE
                    Log.d("animes", it.data.toString())
                    Log.d("animes", it.data?.docs?.get(0).toString())
                    Log.d("animes", it.data?.docs?.get(0).toString())
                    var intent = Intent(this, DetailAnimeActivity::class.java).apply {
                        putExtra(EXTRA_ANIME_ID, it.data?.docs?.get(0)?.anilistId)
                    }
                    startActivity(intent)
                }
                Status.LOADING -> {
                    pb_upload.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    pb_upload.visibility = View.INVISIBLE
                    Log.d("errors", it.data.toString())
                    it.message?.let { it1 -> Log.d("error ${it.status}", it1) }
                    Toast.makeText(this, "NOT FOUND!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getPermition() {
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, Array<String>(1) {  Manifest.permission.CAMERA  },
                MY_CAMERA_REQUEST_CODE );
        }
    }

    private fun setupUI() {
        btn_capture.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

        btn_upload.setOnClickListener {
            var imageFormated = BitmapToString(imageData)
            mainVewModel.fetchImage(imageFormated)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            if (imageBitmap != null) {
                iv_preview.setImageBitmap(imageBitmap)
                imageData = imageBitmap
            }
        }
    }

    fun BitmapToString(image: Bitmap?): String {
        var baos: ByteArrayOutputStream = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 60, baos)
        var b: ByteArray = baos.toByteArray()
        val imageToUpload = Base64.encodeToString(b, Base64.DEFAULT)
        return imageToUpload
    }
}