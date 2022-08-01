package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sopt30th.R
import com.example.sopt30th.databinding.FragmentOnboarding1Binding
import com.example.sopt30th.presentation.ui.base.BaseFragment

class Onboarding1Fragment : BaseFragment<FragmentOnboarding1Binding>() {
    override val TAG: String
        get() = Onboarding1Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onboarding()
    }

    private fun onboarding() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding1Fragment_to_onboarding2Fragment)
        }
    }
}