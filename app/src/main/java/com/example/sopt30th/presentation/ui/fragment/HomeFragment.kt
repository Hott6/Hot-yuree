package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt30th.databinding.FragmentHomeBinding
import com.example.sopt30th.presentation.adapter.TabViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")
    private lateinit var sampleTabViewPagerAdapter: TabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(HomeFollowingFragment(), HomeFollowerFragment())

        sampleTabViewPagerAdapter = TabViewPagerAdapter(this)
        sampleTabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.homevp.adapter = sampleTabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.hometl,binding.homevp) {tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}