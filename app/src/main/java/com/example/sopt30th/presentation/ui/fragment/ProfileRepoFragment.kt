package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.sopt30th.R
import com.example.sopt30th.data.model.response.ResponseRepoInfo
import com.example.sopt30th.databinding.FragmentRepoBinding
import com.example.sopt30th.presentation.adapter.ProfileRepoAdapter
import com.example.sopt30th.presentation.ui.base.BaseFragment
import com.example.sopt30th.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepoFragment : BaseFragment<FragmentRepoBinding>() {
    private lateinit var repoAdapter: ProfileRepoAdapter
    override val TAG: String
        get() = ProfileRepoFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_repo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoAdapter = ProfileRepoAdapter()
        binding.rvRepo.adapter = repoAdapter


        initRepoNetwork()
    }

    private fun initRepoNetwork() {
        val call: Call<List<ResponseRepoInfo>> = ServiceCreator.githubApiService.getRepoInfo()

        call.enqueue(object : Callback<List<ResponseRepoInfo>> {
            override fun onResponse(
                call: Call<List<ResponseRepoInfo>>,
                response: Response<List<ResponseRepoInfo>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        repoAdapter.repoList.addAll(it.toMutableList())
                        repoAdapter.notifyDataSetChanged()
                    }
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<List<ResponseRepoInfo>>, t: Throwable) {
            }
        })
    }
}