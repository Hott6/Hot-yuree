package com.example.a220402.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.databinding.ActivitySettingBinding
import com.example.a220402.util.LoginSharedPreferences

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
            LoginSharedPreferences.setLogout(this)
            showToast("자동로그인 해제되었습니다")
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}