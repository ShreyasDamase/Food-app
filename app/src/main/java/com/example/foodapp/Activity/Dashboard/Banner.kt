package com.example.foodapp.Activity.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.foodapp.Domain.BannerModel
import com.example.foodapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@Composable
fun Banners (banners: SnapshotStateList<BannerModel>,showBannerLoading  : Boolean){
    if (showBannerLoading){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), contentAlignment = Alignment.Center){
            CircularProgressIndicator()

        }
    }else{
Banner(banners, showBannerLoading)
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(banners: SnapshotStateList<BannerModel>, showBannerLoading: Boolean){

    AutoSlidingCarosel(banners=banners)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun  AutoSlidingCarosel(modifier: Modifier= Modifier, pagerState: PagerState = remember {PagerState()}, banners: List<BannerModel>){
val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    Column(modifier= Modifier.fillMaxSize()){
        HorizontalPager(count =banners.size , state=pagerState) {
            page->
            AsyncImage(
                model= ImageRequest.Builder(LocalContext.current).data(banners[page].image).build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp)

            )

            DotIndicator(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp),
                totalDot = banners.size,
                selectedIndex = if(isDragged) {
                    pagerState.currentPage
                }else pagerState.currentPage ,
//                selectedColor = TODO(),
//                inSelectedColor = TODO(),
                dotSize = 8.dp
            )
        }
    }
}

@Composable
fun DotIndicator(modifier: Modifier= Modifier ,totalDot:Int,selectedIndex:Int,selectedColor: Color=
    colorResource(R.color.orange), unSelectedColor: Color=colorResource(R.color.grey),dotSize: Dp
 ){
    LazyRow(modifier= Modifier
        .fillMaxSize()
        .wrapContentWidth()
        .wrapContentHeight()){
        items(totalDot){
            index ->
                IndicatorDot( color=  if (index == selectedIndex )  selectedColor  else unSelectedColor ,
                    size = dotSize , modifier = Modifier)
            if (index==totalDot-1) Spacer(modifier= Modifier.padding(horizontal = 2.dp ))

        }
    }
}
@Composable
fun IndicatorDot(modifier: Modifier= Modifier,  size:Dp ,color: Color){
    Box(modifier= Modifier
        .size(size)
        .clip(CircleShape)
        .background(color)){

    }
}