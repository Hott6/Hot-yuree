package com.example.sopt30th.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.sopt30th.R
import com.example.sopt30th.databinding.ActivitySettingBinding
import com.example.sopt30th.presentation.ui.base.BaseActivity
import com.example.sopt30th.util.LoginSharedPreferences
import com.example.sopt30th.util.showToast

class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLogout()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
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