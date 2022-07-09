package com.example.sopt30th.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt30th.databinding.ItemHomeFollowerListBinding
import com.example.sopt30th.response.ResponseUserInfo

class HomeFollowerAdapter : RecyclerView.Adapter<HomeFollowerAdapter.FollowerViewHolder>() {

    val followerList = mutableListOf<ResponseUserInfo>()

    class FollowerViewHolder(
        private val binding: ItemHomeFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserInfo) {
            binding.follower = data //바인딩 이름, 설명 다 할 필요 없이 코드가 한 줄로 줄어들었음~
            Glide.with(binding.ivProfile).load(data.avatar_url)
                .circleCrop()
                .into(binding.ivProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemHomeFollowerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FollowerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size
}
