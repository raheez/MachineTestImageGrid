package com.example.machinetest.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.machinetest.ImageClickInterface
import com.example.machinetest.ImageListAdapter
import com.example.machinetest.R
import com.example.machinetest.databinding.ActivityMainBinding
import com.example.machinetest.model.ImageListModel
import com.example.machinetest.viewModel.ImageListViewModel

class MainActivity : AppCompatActivity(), ImageClickInterface {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var gridLayoutManager: GridLayoutManager
    private var listOfImages = mutableListOf<ImageListModel>()
    private lateinit var adapter: ImageListAdapter
    private val viewModel by viewModels<ImageListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setOrientation()
        setRecyclerView()
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
        //for tablet device support both portrait and landscape
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun setRecyclerView() {

        val spanCount = resources.getInteger(R.integer.span_count)
        viewModel.items.observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter = ImageListAdapter(this, it, this)
                gridLayoutManager = GridLayoutManager(this, spanCount)
                mainBinding.recyclerView.layoutManager = gridLayoutManager
                mainBinding.recyclerView.adapter = adapter
                initialiseListeners()
            }
        })
    }

    override fun onImageClick(image: Int) {
        val intent = Intent(this, ImagePreviewActivity::class.java).apply {
            putExtra("SELECTED_IMAGE", image)
            startActivity(this)
        }
    }

}
