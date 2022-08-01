package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.sopt30th.R
import com.example.sopt30th.data.model.response.ResponseUserInfo
import com.example.sopt30th.databinding.FragmentHomeFollowingBinding
import com.example.sopt30th.presentation.adapter.HomeFollowingAdapter
import com.example.sopt30th.presentation.ui.base.BaseFragment
import com.example.sopt30th.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFollowingFragment : BaseFragment<FragmentHomeFollowingBinding>() {
    private lateinit var followingAdapter: HomeFollowingAdapter
    override val TAG: String
        get() = HomeFollowingFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home_following

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

}