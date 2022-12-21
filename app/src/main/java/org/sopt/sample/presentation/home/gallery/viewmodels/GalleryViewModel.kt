package org.sopt.sample.presentation.home.gallery.viewmodels

import ContentUriRequestBody
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.AuthService

class GalleryViewModel : ViewModel(){
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image : LiveData<ContentUriRequestBody>
    get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody){
        _image.value = requestBody
    }
    fun uploadProfileImage(){
        if(image.value == null){

        }
        AuthService.uploadProfileImage(id, image.value!!)
                .enqueue()
        }
    }
}
