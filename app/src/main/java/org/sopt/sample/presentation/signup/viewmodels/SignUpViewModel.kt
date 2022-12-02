package org.sopt.sample.presentation.signup.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.R
import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.data.remote.RequestSignUpDTO
import org.sopt.sample.data.remote.ResponseSignUpDTO
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel(){
    //내부에서 설정하는 자료형은 Mutable로 변경가능하도록 설정
    private val _signUpResult: MutableLiveData<ResponseSignUpDTO> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDTO> = _signUpResult

    private val _errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: LiveData<Int> = _errorMessage

    private val _checkId : MutableLiveData<Boolean> = MutableLiveData()
    val checkId : LiveData<Boolean> = _checkId

    private val _checkPw : MutableLiveData<Boolean> = MutableLiveData()
    val checkPw : LiveData<Boolean> = _checkPw

    private val signUpService = ServicePool.authService


    fun signUp(id:String, password: String, name:String){
        signUpService.signup(
            RequestSignUpDTO(
                id,
                password,
                name
            )
        ).enqueue(object : Callback<ResponseSignUpDTO>{
            override fun onResponse(
                call: Call<ResponseSignUpDTO>,
                response: Response<ResponseSignUpDTO>
            ) {
                if(response.isSuccessful){
                    _signUpResult.value = response.body()
                }else{
                    _errorMessage.value = response.body()?.status
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDTO>, t: Throwable) {
                Log.e("SIGNUP-RESPONSE/FAILURE", "signUp 서버통신 실패, ${t.message}")
            }
        })

    }
}
