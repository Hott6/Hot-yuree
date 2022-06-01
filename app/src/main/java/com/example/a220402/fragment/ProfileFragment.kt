package com.example.a220402.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a220402.R
import com.example.a220402.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private var position = FOLLOWER_POSITION
    private var _binding: FragmentProfileBinding? = null //fragment로 바꿨기 때문에 _binding
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initTransactionEvent()
        initImage() //return 전에 작성해줘야 한다

        return binding.root
    }

    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.uxri)
            .circleCrop()
            .into(binding.image)
    }

    fun initTransactionEvent() {
        val fragment1 = ProfileFollowerFragment()
        val fragment2 = ProfileRepoFragment()

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_main, fragment1)
            .commit()

        binding.followerbtn.setOnClickListener {
            if (position == REPO_POSITION) {
                childFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1)
                    .commit()
                position = FOLLOWER_POSITION
                followerbtn.isSelected = true
                repobtn.isSelected = false
            }
        }

        binding.repobtn.setOnClickListener {
            if (position == FOLLOWER_POSITION) {
                childFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    .commit()
                position = REPO_POSITION
                followerbtn.isSelected = false
                repobtn.isSelected = true
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}