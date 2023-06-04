package com.example.machinetest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.machinetest.databinding.ActivityImagePreviewBinding

class ImagePreviewActivity : AppCompatActivity() {

    //region declaration
    private lateinit var imagePreviewBinding: ActivityImagePreviewBinding
    //endregion

    //region lifecycle-methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagePreviewBinding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(imagePreviewBinding.root)
        checkBundle()
    }
    //endregion

    //region methods
    /**
     *     for extracting the image
     */
    private fun checkBundle() {
        val image = intent.getIntExtra("SELECTED_IMAGE", 0)
        if (image != 0) {
            imagePreviewBinding.imagePreview.let {
                Glide.with(this).load(image).centerCrop().into(it)
            }
        }
    }
    //endregion
}