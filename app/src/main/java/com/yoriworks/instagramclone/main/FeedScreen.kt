package com.yoriworks.instagramclone.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.yoriworks.instagramclone.InstagramCloneViewModel

@Composable
fun FeedScreen(navController: NavController,vm:InstagramCloneViewModel){
    Text(text = "Feed Screen")
}