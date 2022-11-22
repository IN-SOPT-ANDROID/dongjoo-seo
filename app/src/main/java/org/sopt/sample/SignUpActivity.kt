package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.data.remote.RequestSignUpDTO
import org.sopt.sample.data.remote.ResponseSignUpDTO
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpEvent()

//        binding.btnChecksignup.setOnClickListener {
//            if (!binding.etEmail.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank()&& !binding.etPw.text.isNullOrBlank()) {
//                Toast.makeText(this, "회원 정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
//            }
//            else if (binding.etEmail.text.length in 6..10 && binding.etPw.text.length in 8..12) {
//                val intent = Intent(this, LoginActivity::class.java)
//                binding.apply {
//
//                }
//                intent.putExtra("id", binding.etEmail.text.toString())
//                intent.putExtra("pw", binding.etPw.text.toString())
//                intent.putExtra("mbti", binding.etName.text.toString())
//                setResult(RESULT_OK, intent)
//                Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "입력된 회원 정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun signUpEvent() {
        with(binding) {
            btnChecksignup.setOnClickListener {
                if (!binding.etEmail.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank() && !binding.etName.text.isNullOrBlank()) {
                    val signUpInfo = SignUpInfo(
                        binding.etEmail.text.toString(),
                        binding.etPw.text.toString(),
                        binding.etName.text.toString(),
                    )

                    signUpNetwork(signUpInfo)
                    finish()
                }else{
                    Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun signUpNetwork(signUpInfo: SignUpInfo) {
        val signUpService = ServicePool.authService
        signUpService
            .signup(RequestSignUpDTO(signUpInfo.email, signUpInfo.password, signUpInfo.name))
            .enqueue(object : Callback<ResponseSignUpDTO>{
                override fun onResponse(
                    call: Call<ResponseSignUpDTO>,
                    response: Response<ResponseSignUpDTO>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()
                        Toast.makeText(this@SignUpActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        Log.e("회원가입 성공", "$result")

                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@SignUpActivity, "Registration failed!", Toast.LENGTH_SHORT).show()
                        Log.e("회원가입 실패", "signup error1")
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDTO>, t: Throwable) {
                    Log.e("회원가입 실패", "signup error2")
                }
            }
            )
    }


}