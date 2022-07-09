package com.example.sopt30th.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sopt30th.fragment.HomeFollowerFragment
import com.example.sopt30th.fragment.HomeFollowingFragment

class TabViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
    return when (position) {
        FOLLOWING_FRAGMENT -> HomeFollowingFragment()
        FOLLOWER_FRAGMENT -> HomeFollowerFragment()
        else -> throw IndexOutOfBoundsException()
    }
}

    companion object {
    const val FOLLOWING_FRAGMENT = 0
    const val FOLLOWER_FRAGMENT = 1
    }
}

