package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sopt30th.R
import com.example.sopt30th.databinding.FragmentOnboarding2Binding
import com.example.sopt30th.presentation.ui.base.BaseFragment

class Onboarding2Fragment : BaseFragment<FragmentOnboarding2Binding>() {
    override val TAG: String
        get() = Onboarding2Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onboarding()
    }

    private fun onboarding() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding2Fragment_to_onboarding3Fragment)
        }
    }
}