package com.example.sopt30th.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt30th.data.RepoData
import com.example.sopt30th.databinding.ItemProfileRepoListBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    val repoList = mutableListOf<RepoData>()

    class RepoViewHolder(
        private val binding: ItemProfileRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData) {
            binding.tvReponame.text = data.reponame
            binding.tvRepointro.text = data.repointroduction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemProfileRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size
}

