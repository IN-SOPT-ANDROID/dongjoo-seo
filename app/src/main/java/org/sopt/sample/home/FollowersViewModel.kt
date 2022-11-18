package org.sopt.sample.home

import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ResponseFollowerListDTO

class FollowersViewModel : ViewModel() {
    val followersList = mutableListOf<ResponseFollowerListDTO.FollowerData>()
}