package org.sopt.sample.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import org.sopt.sample.data.model.response.ResponseFollowerListDTO

class FollowersViewModel : ViewModel() {
    val followersList = mutableListOf<ResponseFollowerListDTO.FollowerData>()
}