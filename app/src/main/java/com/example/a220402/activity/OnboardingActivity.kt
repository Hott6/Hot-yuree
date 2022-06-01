package com.example.a220402.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.R
import com.example.a220402.databinding.ActivityOnboardingBinding
import kotlinx.android.synthetic.main.fragment_onboarding1.view.*

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }

    override fun finish() {
        if (binding.fcvOnboarding.btn_next.isSelected)
        super.finish()
    }
}