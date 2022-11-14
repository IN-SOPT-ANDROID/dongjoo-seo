package org.sopt.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.home.data.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val authService = ApiFactory.create<AuthService>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        clickLogin()
        clickSignup()
    }

    private fun clickLogin(){
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPw.text.toString()
            loginEvent(email, password)
        }
    }
    private fun clickSignup() {
        //회원가입 버튼 클릭 시
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun loginEvent(email: String, password: String) {
        //로그인 버튼 클릭 시
        binding.btnLogin.setOnClickListener {
            val loginService = ServicePool.authService
            loginService
                .login(RequestLoginDTO(email, password))
                .enqueue(object : Callback<ResponseLoginDTO> {
                    override fun onResponse(
                        call: Call<ResponseLoginDTO>,
                        response: Response<ResponseLoginDTO>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("로그인 성공", "login success")
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT)
                                .show()
                            Log.e("로그인 실패", "login error1")
                        }

                    }

                    override fun onFailure(call: Call<ResponseLoginDTO>, t: Throwable) {
                        Log.e("로그인 실패", "login error2")
                    }
                }
                )
        }
    }


    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_LONG).show()
                    binding.etEmail.setText(result.data?.getStringExtra("email"))
                    binding.etPw.setText(result.data?.getStringExtra("password"))
                }
            }
    }

}