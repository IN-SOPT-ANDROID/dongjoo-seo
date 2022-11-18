package org.sopt.sample.home.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresFollowerService {
    @GET("/api/users")
    fun getFollowerList(@Query("page") page: Int)
            : Call<ResponseFollowerListDTO>
}