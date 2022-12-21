package org.sopt.sample.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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
        setContentView(R.layout.activity_sign_up)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.btnChecksignup.setOnClickListener {
            viewModel.signUp(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
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
    }

    //id, pw 검사 함수
    private fun checkSignUp() {

        if (validId() && validPw()) {
            binding.btnChecksignup.setBackgroundColor(getColor(R.color.purple_200))
            binding.btnChecksignup.isEnabled = true
        }
    }

    private fun validId(): Boolean {
        val id: String = binding.layoutEtId.editText?.text.toString()
        val regexId = Regex("^[A-Za-z0-9]{6,10}\$")

        return if (id.isEmpty()) {
            binding.layoutEtId.error = "아이디를 입력해주세요"
            false
        } else if (!id.matches(regexId)) {
            binding.layoutEtId.error = "아이디 형식이 잘못되었습니다."
            false
        } else {
            binding.layoutEtId.error = null
            true
        }
    }
    private fun validPw(): Boolean {
        val pw: String = binding.layoutEtPw.editText?.text.toString()
        val regexPw = Regex("^.*(?=^.{6,12}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$")

        return if (pw.isEmpty()) {
            binding.layoutEtPw.error = "비밀번호를 입력해주세요"
            false
        } else if (!pw.matches(regexPw)) {
            binding.layoutEtPw.error = "비밀번호 형식이 잘못되었습니다."
            false
        } else {
            binding.layoutEtPw.error = null
            true
        }
    }
}