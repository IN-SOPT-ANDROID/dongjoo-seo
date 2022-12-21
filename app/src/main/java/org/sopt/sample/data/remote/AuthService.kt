package org.sopt.sample.data.remote

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AuthService {
    @POST("api/user/signin")
    fun login(
        @Body request: RequestLoginDTO
    ) : Call<ResponseLoginDTO>


    @POST("api/user/siginup")
    fun signup(
        @Body request: RequestSignUpDTO
    ) : Call<ResponseSignUpDTO>
}