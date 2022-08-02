package com.example.sopt30th.presentation.ui.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.sopt30th.R
import com.example.sopt30th.data.model.response.ResponseUserInfo
import com.example.sopt30th.databinding.ActivityDetailBinding
import com.example.sopt30th.presentation.adapter.ProfileFollowerAdapter
import com.example.sopt30th.presentation.ui.base.BaseActivity

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_detail
    lateinit var data: ResponseUserInfo
    private lateinit var profileFollowerAdapter: ProfileFollowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name")
        binding.tvName.text = name
        val image = intent.getStringExtra("image")
        Glide.with(binding.ivProfileImage).load(image).circleCrop().into(binding.ivProfileImage)

    }
}