package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a220402.databinding.FragmentFollowerBinding

class ProfileFollowerFragment : Fragment() {
    private lateinit var followerAdapter: ProfileFollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("binding이 초기화 안 됐으니 초기화 하시오.")
    //_binding!!을 사용하면 만약에 null 값인 경우 NullPointerException이라는 런타임 에러 띄우며 죽으니 ?: 사용해 안전하게 처리하자

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        followerAdapter = ProfileFollowerAdapter()
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.yr, "최유리", "안드로이드 YB 파트원 치코리타"),
                FollowerData(R.drawable.yj, "최윤정", "안드로이드 YB 파트원 마자용"),
                FollowerData(R.drawable.sb, "김수빈", "안드로이드 OB 파트원 라이츄"),
                FollowerData(R.drawable.jw, "이준원", "안드로이드 YB 파트원 꼬지모"),
                FollowerData(R.drawable.ym, "권용민", "안드로이드 OB 파트원 알통몬")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}