package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.sopt30th.R
import com.example.sopt30th.data.model.response.ResponseUserInfo
import com.example.sopt30th.databinding.FragmentHomeFollowerBinding
import com.example.sopt30th.presentation.adapter.HomeFollowerAdapter
import com.example.sopt30th.presentation.ui.base.BaseFragment
import com.example.sopt30th.util.ItemDecoration
import com.example.sopt30th.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFollowerFragment : BaseFragment<FragmentHomeFollowerBinding>() {
    override val TAG: String
        get() = HomeFollowerFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home_follower
    private lateinit var followerAdapter: HomeFollowerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followerItemDecoration()
        initUserInfoNetwork()
        followerAdapter = HomeFollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
    }

    private fun followerItemDecoration() {
        binding.rvFollower.addItemDecoration(
            ItemDecoration(5)
        )
    }

    private fun initUserInfoNetwork() {
        val call: Call<List<ResponseUserInfo>> = ServiceCreator.githubApiService.getFollowerInfo()

        call.enqueue(object : Callback<List<ResponseUserInfo>> {
            override fun onResponse(
                call: Call<List<ResponseUserInfo>>,
                response: Response<List<ResponseUserInfo>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        followerAdapter.followerList.addAll(it.toMutableList())
                        followerAdapter.notifyDataSetChanged()
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