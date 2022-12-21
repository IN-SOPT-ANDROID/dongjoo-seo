package org.sopt.sample.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.model.response.ResponseGetMusicDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface MusicService {
    @GET("music/list")
    fun getMusicList(
    ):Call<ResponseGetMusicDTO>

    @Multipart
    @POST("music")
    fun registerMusic(
        @Part image: MultipartBody.Part,
        @PartMap request: HashMap<String, RequestBody>
    ):Call<Unit>
}