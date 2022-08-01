package com.example.sopt30th.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.sopt30th.R
import com.example.sopt30th.databinding.FragmentProfileBinding
import com.example.sopt30th.presentation.ui.activity.SettingActivity
import com.example.sopt30th.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private var position = FOLLOWER_POSITION
    override val TAG: String
        get() = ProfileFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransactionEvent()
        initImage() //return 전에 작성해줘야 한다
        clickEvent()
    }

    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.uxri)
            .circleCrop()
            .into(binding.image)
    }

    private fun clickEvent() {
        binding.btnSetting.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }
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