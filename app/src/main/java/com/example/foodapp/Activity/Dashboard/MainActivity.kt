package com.example.foodapp.Activity.Dashboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.foodapp.Activity.Splash.BaseActivity
import com.example.foodapp.Domain.BannerModel
import com.example.foodapp.ViewModel.MainViewModel

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }

    @Composable

    fun MainScreen() {
        val scaffoldState = rememberScaffoldState()
        val banners = remember { mutableStateListOf<BannerModel>() }
        var showBannerLoading by remember { mutableStateOf(true) }
        val viewModel = MainViewModel()
        LaunchedEffect(Unit) {
            viewModel.loadBanner().observeForever {

                banners.clear()
banners.addAll(it)
                showBannerLoading=false
            }
        }
        Scaffold(
            bottomBar = { MyBottomBar() }, scaffoldState = scaffoldState
        ) {
            paddingValues ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues=paddingValues)
            ) {
                         item { TopBar() }
                item { Banners(  banners = banners ,showBannerLoading =showBannerLoading) }

            }
        }
    }
}
