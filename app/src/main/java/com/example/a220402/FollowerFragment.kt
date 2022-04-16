package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a220402.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter(){
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.yr,"최유리", "안드로이드 YB 파트원 치코리타"),
                FollowerData(R.drawable.yj,"최윤정", "안드로이드 YB 파트원 마자용"),
                FollowerData(R.drawable.sb,"김수빈", "안드로이드 OB 파트원 라이츄"),
                FollowerData(R.drawable.jw,"이준원", "안드로이드 YB 파트원 꼬지모"),
                FollowerData(R.drawable.ym,"권용민", "안드로이드 OB 파트원 알통몬")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}