package org.sopt.sample.presentation.login.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.model.request.RequestLoginDTO
import org.sopt.sample.data.model.response.ResponseLoginDTO
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//서버통신은 뷰모델에서
class LoginViewModel : ViewModel() {
    private val _loginResult: MutableLiveData<ResponseLoginDTO> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDTO>
        get() = _loginResult
    private val _errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private val loginService = ServicePool.authService

    fun login(id: String, password: String) {
        loginService.login(
            RequestLoginDTO(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseLoginDTO> {
            override fun onResponse(
                call: Call<ResponseLoginDTO>,
                response: Response<ResponseLoginDTO>
            ) {
                Log.e("LOGIN-RESPONSE/SUCCESS", "login 서버는 통신이 되었는데,, 그래서? , ${response}")
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                } else {
                    _errorMessage.value = response.body()?.status
                }
            }

            override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                Log.e("LOGIN-RESPONSE/FAILURE", "login 서버통신 실패, ${t.message}")
            }
        })
    }

}