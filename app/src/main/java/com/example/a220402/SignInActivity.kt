@file:Suppress("UnusedImport")

package com.example.a220402

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a220402.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, HomeActivity::class.java)
        val intent2 = Intent(this, SignUpActivity::class.java)

        binding.btn.setOnClickListener {
            if (binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(intent)
            }
        }
        binding.btnJ.setOnClickListener {
            startActivity(intent2)
        }
    }
}