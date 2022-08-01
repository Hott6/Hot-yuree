package com.example.sopt30th.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt30th.databinding.ActivitySettingBinding
import com.example.sopt30th.util.LoginSharedPreferences
import com.example.sopt30th.util.showToast

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLogout()
    }

    private fun setLogout() {
        binding.btnLogout.setOnClickListener {
            LoginSharedPreferences.setLogout()
            showToast("자동로그인 해제되었습니다")
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}