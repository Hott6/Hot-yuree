package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sopt30th.R
import com.example.sopt30th.databinding.FragmentOnboarding3Binding
import com.example.sopt30th.presentation.ui.base.BaseFragment

class Onboarding3Fragment : BaseFragment<FragmentOnboarding3Binding>() {
    override val TAG: String
        get() = Onboarding3Fragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_onboarding3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onboarding()
        initImage()
    }

    private fun onboarding() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding3Fragment_to_signInActivity)
            requireActivity().finish()
        }
    }

    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.uxri)
            .circleCrop()
            .into(binding.ivImage)
    }
}