package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChecksignup.setOnClickListener {
            if (!binding.etId.text.isNullOrBlank() && !binding.etPw.text.isNullOrBlank()&& !binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(this, "회원 정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
            else if (binding.etId.text.length in 6..10 && binding.etPw.text.length in 8..12) {
                val intent = Intent(this, LoginActivity::class.java)
                binding.apply {

                }
                intent.putExtra("id", binding.etId.text.toString())
                intent.putExtra("pw", binding.etPw.text.toString())
                intent.putExtra("mbti", binding.etMbti.text.toString())
                setResult(RESULT_OK, intent)
                Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "입력된 회원 정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}