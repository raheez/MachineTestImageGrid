package com.example.machinetest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.machinetest.R
import com.example.machinetest.model.ImageListModel

class ImageListViewModel : ViewModel() {

    private var _imageListMutableLiveData: MutableLiveData<List<ImageListModel>> = MutableLiveData(listOf())

    val imageListLiveData: LiveData<List<ImageListModel>> = _imageListMutableLiveData

    init {
        createImageList()
    }

    private fun createImageList() {

        val listOfImages = mutableListOf<ImageListModel>()
        listOfImages.clear()
        listOfImages.add(ImageListModel(R.drawable.bird,"bird"))
        listOfImages.add(ImageListModel(R.drawable.bird_face,"bird face"))
        listOfImages.add(ImageListModel(R.drawable.bridge,"bridge"))
        listOfImages.add(ImageListModel(R.drawable.butter_fly,"butter fly"))
        listOfImages.add(ImageListModel(R.drawable.cactus,"cactus"))
        listOfImages.add(ImageListModel(R.drawable.cat,"cat"))
        listOfImages.add(ImageListModel(R.drawable.eyes,"eyes"))
        listOfImages.add(ImageListModel(R.drawable.fish,"fish"))
        listOfImages.add(ImageListModel(R.drawable.flower,"flower"))
        listOfImages.add(ImageListModel(R.drawable.horse,"horse"))
        listOfImages.add(ImageListModel(R.drawable.insect,"insect"))
        listOfImages.add(ImageListModel(R.drawable.lady_bugs,"lady bugs"))
        listOfImages.add(ImageListModel(R.drawable.leaf,"leaf"))
        listOfImages.add(ImageListModel(R.drawable.owl,"owl"))
        listOfImages.add(ImageListModel(R.drawable.sea,"sea"))
        listOfImages.add(ImageListModel(R.drawable.water_lily,"water lily"))
        listOfImages.add(ImageListModel(R.drawable.white_bird,"white bird"))
        listOfImages.add(ImageListModel(R.drawable.zebra,"zebra"))
        _imageListMutableLiveData.postValue(listOfImages);
    }


}