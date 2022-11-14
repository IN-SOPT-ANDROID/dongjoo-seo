package org.sopt.sample.home.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    fun login(
        @Body request:RequestLoginDTO
    ) : Call<ResponseLoginDTO>
    @POST("api/user/siginup")
    fun signup(
        @Body request: RequestSignUpDTO
    ) : Call<ResponseSignUpDTO>
}