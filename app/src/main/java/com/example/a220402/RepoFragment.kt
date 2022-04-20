package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a220402.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {
    private lateinit var repoAdapter: RepoAdapter
    private var _binding : FragmentRepoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter(){
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter

        repoAdapter.repoList.addAll(
            listOf(
                RepoData("안드로이드 과제 레포지토리", "안드 레포! 안드짱 안드짱 안드짱짱짱짱"),
                RepoData("최유리 개인 과제 레포지토리", "유리 과제 레포입니다"),
                RepoData("금잔디 6조 레포지토리", "금잔디 6조 레포입니다"),
                RepoData("기획 과제 레포지토리", "기획 과제 레포입니다"),
                RepoData("아요 과제 레포지토리", "아요 과제 레포입니다"),
                RepoData("서버 과제 레포지토리", "서버 과제 레포입니다")
            )
        )
        repoAdapter.notifyDataSetChanged()
    }
}