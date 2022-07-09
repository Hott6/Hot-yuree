package com.example.sopt30th.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt30th.databinding.ItemHomeFollowingListBinding
import com.example.sopt30th.response.ResponseUserInfo

class HomeFollowingAdapter : RecyclerView.Adapter<HomeFollowingAdapter.FollowingViewHolder>() {

    val followingList = mutableListOf<ResponseUserInfo>()

    class FollowingViewHolder(
        private val binding: ItemHomeFollowingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserInfo) {
            binding.following = data //바인딩 이름, 설명 다 할 필요 없이 코드가 한 줄로 줄어들었음~
            Glide.with(binding.ivProfile).load(data.avatar_url)
                .circleCrop()
                .into(binding.ivProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding =
            ItemHomeFollowingListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FollowingViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.onBind(followingList[position])
    }

    override fun getItemCount(): Int = followingList.size
}