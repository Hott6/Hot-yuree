package com.example.sopt30th.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt30th.data.model.response.ResponseUserInfo
import com.example.sopt30th.databinding.ItemProfileFollowerListBinding

class ProfileFollowerAdapter(private val itemClick: (ResponseUserInfo) -> (Unit)
) : RecyclerView.Adapter<ProfileFollowerAdapter.FollowerViewHolder>() {

    val followerList = mutableListOf<ResponseUserInfo>()

    inner class FollowerViewHolder(
        private val binding: ItemProfileFollowerListBinding,
        private val itemClick: (ResponseUserInfo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserInfo) {
            binding.follower = data

            binding.root.setOnClickListener{
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemProfileFollowerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FollowerViewHolder(binding, itemClick)
    }


    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size
}

