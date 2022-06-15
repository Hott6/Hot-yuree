package com.example.sopt30th.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt30th.databinding.ItemProfileRepoListBinding
import com.example.sopt30th.response.ResponseRepoInfo

class ProfileRepoAdapter : RecyclerView.Adapter<ProfileRepoAdapter.RepoViewHolder>() {
    val repoList = mutableListOf<ResponseRepoInfo>()

    class RepoViewHolder(
        private val binding: ItemProfileRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseRepoInfo) {
            binding.repo = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemProfileRepoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return RepoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size
}

