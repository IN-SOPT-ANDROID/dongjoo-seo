package org.sopt.sample.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import org.sopt.sample.HomeActivity
import org.sopt.sample.R
import org.sopt.sample.SignUpInfo
import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.data.remote.RequestSignUpDTO
import org.sopt.sample.data.remote.ResponseSignUpDTO
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.login.LoginActivity
import org.sopt.sample.presentation.signup.viewmodels.SignUpViewModel
import org.sopt.sample.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    //viewmodel 컴포넌트 가져오기
    private val viewModel by viewModels<SignUpViewModel>()
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Assign the component to a property in the binding class.
        viewModel.signUpResult.observe(this) {
            checkSignUp()
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        viewModel.errorMessage.observe(this) {
            showToast("$it 오류 발생")
        }

    }

    //id, pw 검사 함수
    private fun checkSignUp(){
        val regexId = Regex("^[A-Za-z0-9]{6,10}\$")
        val regexPw = Regex("^.*(?=^.{6,12}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$")

        if (binding.etId.text.matches(regexId) && binding.etPw.text.matches(regexPw)) {
            binding.btnChecksignup.setBackgroundColor(getColor(R.color.purple_200))
            binding.btnChecksignup.isEnabled = true
        }
    }
}