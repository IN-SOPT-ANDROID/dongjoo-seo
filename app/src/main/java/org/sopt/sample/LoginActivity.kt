package org.sopt.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private var id: String? = null
    private var password: String? = null
    private var mbti: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        clickLogin()
        clickSignup()
    }

    //콜백 선언
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_LONG).show()
                    val id = result.data?.getStringExtra("id") ?: ""
                    val password = result.data?.getStringExtra("password") ?: ""
                    binding.etId.setText(id)
                    binding.etPw.setText(password)
                }
            }
    }

    private fun clickLogin() {
        //로그인 버튼 클릭 시
        binding.btnLogin.setOnClickListener {
            if (binding.etId.text.length in 6..10 && binding.etPw.text.length in 8..12) {
                //로그인 성공 시에 HomeActivity 로 이동
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("mbti", mbti)
                startActivity(intent)
            } else {
                Toast.makeText(this, "입력하신 정보를 다시 확인해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun clickSignup() {
        //회원가입 버튼 클릭 시
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}