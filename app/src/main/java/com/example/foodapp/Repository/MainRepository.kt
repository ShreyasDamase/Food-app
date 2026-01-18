package com.example.foodapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.Domain.BannerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private  val firebaseDatabase = FirebaseDatabase.getInstance()
    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        val  listData= MutableLiveData<MutableList<BannerModel>>()
        val ref= firebaseDatabase.getReference("Banners")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
val list = mutableListOf<BannerModel>()
                for (childsnapshot in snapshot.children){
                    val item = childsnapshot.getValue((BannerModel::class.java))
                item?.let { list.add(it) }
                }
                listData.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return  listData

    }

}