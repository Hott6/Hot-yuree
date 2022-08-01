package com.example.sopt30th.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt30th.data.model.response.ResponseRepoInfo
import com.example.sopt30th.databinding.FragmentRepoBinding
import com.example.sopt30th.presentation.adapter.ProfileRepoAdapter
import com.example.sopt30th.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepoFragment : Fragment() {
    private lateinit var repoAdapter: ProfileRepoAdapter
    private var _binding : FragmentRepoBinding? = null
    private val binding get() = _binding ?: error("binding이 초기화 안 됐으니 초기화 하시오.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRepoNetwork()
        repoAdapter = ProfileRepoAdapter()
        binding.rvRepo.adapter = repoAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}