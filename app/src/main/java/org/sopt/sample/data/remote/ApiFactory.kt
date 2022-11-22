package org.sopt.sample.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiFactory {
    //by lazy 사용 이유 : 내부 스레드를 동기화해서 데이터를 가져오기 -> 조금 더 안전하게 객체 사용
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
    inline fun <reified T> createReqres() : T = reqresRetrofit.create<T>(T::class.java)

}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val reqresFollowerService = ApiFactory.createReqres<ReqresFollowerService>()
}