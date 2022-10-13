package org.sopt.sample.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.LayoutRvHeaderBinding
import org.sopt.sample.databinding.LayoutGithubRepoBinding
import org.sopt.sample.home.data.Repo

class RepoAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER = 0
    private val ITEM = 1
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<Repo> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER
        } else ITEM
    }

    class RepoViewHolder(
        private val binding: LayoutGithubRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Repo) {
            binding.imgGithubRepo.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvGithubRepoName.text = data.name
            binding.tvGithubRepoAuthor.text = data.author
        }
    }

    class HeaderViewHolder(
        private val binding: LayoutRvHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val binding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
//        return RepoViewHolder(binding)
        return when (viewType) {
            HEADER -> {
                val layoutRvHeaderBinding = LayoutRvHeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(layoutRvHeaderBinding)
            }

            else -> {
                val githubRepoBinding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
                RepoViewHolder(githubRepoBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
//                holder.onBind(repoList[position])
            }
            is RepoViewHolder -> {
                holder.onBind(repoList[position])
            }
        }
    }

    override fun getItemCount() = repoList.size

    fun setRepoList(repoList: List<Repo>) {
        this.repoList = repoList.toList()
        notifyDataSetChanged()
    }
}
