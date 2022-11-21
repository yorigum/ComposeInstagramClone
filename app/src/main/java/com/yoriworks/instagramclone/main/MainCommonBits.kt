package com.yoriworks.instagramclone.main

import android.app.Notification
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yoriworks.instagramclone.InstagramCloneViewModel

@Composable
fun NotificationMessage(vm:InstagramCloneViewModel){
    val notifState = vm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null){
        Toast.makeText(LocalContext.current,notifMessage,Toast.LENGTH_LONG).show()
    }

}