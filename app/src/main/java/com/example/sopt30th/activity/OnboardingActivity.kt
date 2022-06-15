package com.example.sopt30th.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt30th.R
import com.example.sopt30th.databinding.ActivityOnboardingBinding
import com.example.sopt30th.util.LoginSharedPreferences
import kotlinx.android.synthetic.main.fragment_onboarding1.view.*

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        LoginSharedPreferences.init(this)
    }

    override fun finish() {
        if (binding.fcvOnboarding.btn_next.isSelected)
        super.finish()
    }
}