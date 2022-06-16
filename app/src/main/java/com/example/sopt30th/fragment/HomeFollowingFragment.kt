package com.example.sopt30th.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt30th.adapter.HomeFollowingAdapter
import com.example.sopt30th.databinding.FragmentHomeFollowingBinding
import com.example.sopt30th.response.ResponseUserInfo
import com.example.sopt30th.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFollowingFragment : Fragment() {

    private lateinit var followingAdapter: HomeFollowingAdapter
    private var _binding: FragmentHomeFollowingBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeFollowingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInfoNetwork()
        followingAdapter = HomeFollowingAdapter()
        binding.rvFollowing.adapter = followingAdapter
    }

    private fun initUserInfoNetwork() {
        val call: Call<List<ResponseUserInfo>> = ServiceCreator.githubApiService.getFollowingInfo()

        call.enqueue(object : Callback<List<ResponseUserInfo>> {
            override fun onResponse(
                call: Call<List<ResponseUserInfo>>,
                response: Response<List<ResponseUserInfo>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        followingAdapter.followingList.addAll(it.toMutableList())
                        followingAdapter.notifyDataSetChanged()
                    }
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<List<ResponseUserInfo>>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}