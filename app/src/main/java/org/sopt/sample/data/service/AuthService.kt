package org.sopt.sample.data.service

import org.sopt.sample.data.model.request.RequestLoginDTO
import org.sopt.sample.data.model.request.RequestSignUpDTO
import org.sopt.sample.data.model.response.ResponseLoginDTO
import org.sopt.sample.data.model.response.ResponseSignUpDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    fun login(
        @Body request: RequestLoginDTO
    ): Call<ResponseLoginDTO>

    @POST("api/user/siginup")
    fun signup(
        @Body request: RequestSignUpDTO
    ): Call<ResponseSignUpDTO>
}
