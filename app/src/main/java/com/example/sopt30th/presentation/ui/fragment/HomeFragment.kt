package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.sopt30th.R
import com.example.sopt30th.databinding.FragmentHomeBinding
import com.example.sopt30th.presentation.adapter.TabViewPagerAdapter
import com.example.sopt30th.presentation.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var sampleTabViewPagerAdapter: TabViewPagerAdapter
    override val TAG: String
        get() = HomeFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        val fragmentList = listOf(HomeFollowingFragment(), HomeFollowerFragment())

        sampleTabViewPagerAdapter = TabViewPagerAdapter(this)
        sampleTabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.homevp.adapter = sampleTabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.hometl, binding.homevp) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}