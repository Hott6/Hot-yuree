package com.example.sopt30th.presentation.ui.activity

import android.os.Bundle
import com.example.sopt30th.R
import com.example.sopt30th.databinding.ActivityOnboardingBinding
import com.example.sopt30th.presentation.ui.base.BaseActivity
import com.example.sopt30th.util.LoginSharedPreferences

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_onboarding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginSharedPreferences.init(this)
    }
}