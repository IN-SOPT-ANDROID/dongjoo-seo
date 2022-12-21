package org.sopt.sample.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.sample.data.service.AuthInterceptor
import org.sopt.sample.data.service.AuthService
import org.sopt.sample.data.service.MusicService
import org.sopt.sample.data.service.ReqresFollowerService
import retrofit2.Retrofit

object ApiFactory {

    private val client by lazy {
        OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    //by lazy 사용 이유 : 내부 스레드를 동기화해서 데이터를 가져오기 -> 조금 더 안전하게 객체 사용
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)


    val reqresRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    inline fun <reified T> createReqres(): T = reqresRetrofit.create<T>(T::class.java)


    val retrofitMusic: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.34.53.11:8080")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
    }

    inline fun <reified T> createMusic(): T = retrofitMusic.create<T>(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val reqresFollowerService = ApiFactory.createReqres<ReqresFollowerService>()
    val musicService = ApiFactory.createMusic<MusicService>()
}