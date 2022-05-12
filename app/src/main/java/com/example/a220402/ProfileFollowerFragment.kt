package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a220402.databinding.FragmentFollowerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFollowerFragment : Fragment() {
    private lateinit var followerAdapter: ProfileFollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화되지 않았습니다")
    var responseData = mutableListOf<ResponseUserInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInfoNetwork()
        followerAdapter = ProfileFollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
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
                        followerAdapter.followerList = it.toMutableList()
                        followerAdapter.notifyDataSetChanged()
                    }
                } else {
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

/*
override fun onResponse(
    call: Call<ResponseUserInfo>,
    response: Response<List<ResponseUserInfo>>
) {
    if (response.isSuccessful) {
        Log.d("uxriServer", "isSuccessful")
        responseData=response.body()
        if(followerAdapter.followerList != response.body()){
            Log.d("uxriServer", "isSuccessful")
            if(response.body()!=null){
                Log.d("uxriServer", "isSuccessful")
                followerAdapter.followerList.addAll(response.body()!!)
            }
        }

    } else {
        Toast.makeText(requireContext(), "깃허브 팔로워 조회 실패", Toast.LENGTH_LONG).show()
    }
}

override fun onFailure(call: Call<List<ResponseUserInfo>>, t: Throwable) {
    Log.e("NetworkTest", "error:$t") //오류처리 코드
}
*/
