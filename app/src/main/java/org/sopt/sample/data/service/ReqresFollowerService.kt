package org.sopt.sample.data.service

import org.sopt.sample.data.model.response.ResponseFollowerListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresFollowerService {
    @GET("/api/users")
    fun getFollower(@Query("page") page: Int)
            : Call<ResponseFollowerListDTO>
}