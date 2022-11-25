package org.sopt.sample.presentation.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.data.remote.RequestLoginDTO
import org.sopt.sample.data.remote.ResponseLoginDTO
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//서버통신은 뷰모델에서
class LoginViewModel : ViewModel() {
    private val _loginResult: MutableLiveData<ResponseLoginDTO> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDTO> = _loginResult

    private val loginService = ServicePool.authService

    fun login(email: String, password: String) {
        loginService.login(
            RequestLoginDTO(
                email,
                password
            )
        ).enqueue(object: Callback<ResponseLoginDTO>{
            override fun onResponse(
                call: Call<ResponseLoginDTO>,
                response: Response<ResponseLoginDTO>
            ) {
                if(response.isSuccessful){
                    _loginResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}