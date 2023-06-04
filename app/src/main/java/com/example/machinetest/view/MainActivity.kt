package com.example.machinetest.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.example.machinetest.ImageClickInterface
import com.example.machinetest.ImageListAdapter
import com.example.machinetest.R
import com.example.machinetest.databinding.ActivityMainBinding
import com.example.machinetest.model.ImageListModel

class MainActivity : AppCompatActivity(), ImageClickInterface {
    private lateinit var mainBinding :  ActivityMainBinding
    private lateinit var gridLayoutManager : GridLayoutManager
    private var listOfImages  = mutableListOf<ImageListModel>()
    private lateinit var adapter  : ImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setOrientation()
        setRecyclerView()
        initialiseListeners()
    }

    private fun initialiseListeners() {
        mainBinding.editTextSearch.doAfterTextChanged {
            val strChar = it.toString()
            adapter.filter.filter(strChar.lowercase())
            adapter.notifyDataSetChanged()
        }

        mainBinding.searchImageViewClose.setOnClickListener {
            mainBinding.editTextSearch.setText("")
            adapter.notifyDataSetChanged()
        }
    }

    private fun setOrientation() {
        val isTablet = resources.getBoolean(R.bool.isTablet)

//        if (isTablet && resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        } else {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        }

        if (!isTablet){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun setRecyclerView() {

        listOfImages.add(ImageListModel(R.drawable.bird,"bird"))
        listOfImages.add(ImageListModel(R.drawable.bird_face,"bird_face"))
        listOfImages.add(ImageListModel(R.drawable.bridge,"bridge"))
        listOfImages.add(ImageListModel(R.drawable.butter_fly,"butter_fly"))
        listOfImages.add(ImageListModel(R.drawable.cactus,"cactus"))
        listOfImages.add(ImageListModel(R.drawable.cat,"cat"))
        listOfImages.add(ImageListModel(R.drawable.eyes,"eyes"))
        listOfImages.add(ImageListModel(R.drawable.fish,"fish"))
        listOfImages.add(ImageListModel(R.drawable.flower,"flower"))
        listOfImages.add(ImageListModel(R.drawable.horse,"horse"))
        listOfImages.add(ImageListModel(R.drawable.insect,"insect"))
        listOfImages.add(ImageListModel(R.drawable.lady_bugs,"lady_bugs"))
        listOfImages.add(ImageListModel(R.drawable.leaf,"leaf"))
        listOfImages.add(ImageListModel(R.drawable.owl,"owl"))
        listOfImages.add(ImageListModel(R.drawable.sea,"sea"))
        listOfImages.add(ImageListModel(R.drawable.water_lily,"water_lily"))
        listOfImages.add(ImageListModel(R.drawable.white_bird,"white_bird"))
        listOfImages.add(ImageListModel(R.drawable.zebra,"zebra"))


        adapter = ImageListAdapter(this,listOfImages,this)


        val spanCount = resources.getInteger(R.integer.span_count)
        gridLayoutManager = GridLayoutManager(this,spanCount)
        mainBinding.recyclerView.layoutManager   = gridLayoutManager
        mainBinding.recyclerView.adapter = adapter

    }

    override fun onImageClick(image: Int) {

    }

}
