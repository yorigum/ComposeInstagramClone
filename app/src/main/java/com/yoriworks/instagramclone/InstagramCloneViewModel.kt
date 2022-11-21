package com.yoriworks.instagramclone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.yoriworks.instagramclone.data.Event
import com.yoriworks.instagramclone.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val USERS = "users"

@HiltViewModel
class InstagramCloneViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel(){
    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)

    fun onSignup(username:String,email:String,pass:String){
        inProgress.value = true

        db.collection(USERS).whereEqualTo("username",username).get()
            .addOnSuccessListener {
                documents->
                if(documents.size()>0){
handleException(customMessage = "username already exist")
                }else{
                    auth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener{
                            task->
                            if(task.isSuccessful){
                                signedIn.value = true
                            }
                            else{
                                handleException(task.exception,"Signup Failed")
                            }
                            inProgress.value = false
                        }
                }
            }
            .addOnFailureListener{}
    }

    fun handleException(exception: Exception? = null,customMessage: String =""){
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if(customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)

    }
}