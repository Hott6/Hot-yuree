package com.example.a220402.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.a220402.R
import com.example.a220402.databinding.FragmentOnboarding3Binding

class Onboarding3Fragment : Fragment() {
    private var _binding: FragmentOnboarding3Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding3Binding.inflate(layoutInflater, container, false)

        onboarding()
        initImage()
        return binding.root
    }

    private fun onboarding() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding3Fragment_to_signInActivity)
        }
    }

    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.uxri)
            .circleCrop()
            .into(binding.ivImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}