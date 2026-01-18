package com.example.foodapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.Domain.BannerModel
import com.example.foodapp.Repository.MainRepository

class MainViewModel : ViewModel(){
    private val repository = MainRepository()
    fun loadBanner(): LiveData<MutableList<BannerModel>>{
return repository.loadBanner()
    }
}