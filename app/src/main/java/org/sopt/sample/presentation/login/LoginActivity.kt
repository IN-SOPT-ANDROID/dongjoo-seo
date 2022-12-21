package org.sopt.sample.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import org.sopt.sample.HomeActivity
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.login.viewmodels.LoginViewModel
import org.sopt.sample.presentation.signup.SignUpActivity
import org.sopt.sample.showToast

class LoginActivity : AppCompatActivity() {
    //LiveData가 저장되어 있는 ViewModel
    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: ActivityLoginBinding

//    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etId.text.toString(),
                binding.etPw.text.toString()
            )
        }

        viewModel.loginResult.observe(this) {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        }
        viewModel.errorMessage.observe(this) {
            showToast("$it 오류 발생")
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}