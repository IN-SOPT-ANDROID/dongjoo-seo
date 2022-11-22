package org.sopt.sample.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sample.databinding.LayoutGithubRepoBinding
import org.sopt.sample.databinding.LayoutRvHeaderBinding
import org.sopt.sample.data.RepoData
//import org.sopt.sample.util.loadCircleImage

const val HEADER = 0
const val ITEM = 1

class RepoListAdapter(private val title: RepoData.Title) :
    ListAdapter<RepoData.RepoInfo, RepoListAdapter.GithubViewHolder>(
        DiffutilCallback()
    )
{


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            else -> ITEM
        }
    }

    abstract class GithubViewHolder(protected val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onBind(input: RepoInput)
    }

    class HeaderViewHolder(binding: LayoutRvHeaderBinding) : GithubViewHolder(binding){
        override fun onBind(input: RepoInput) {
            if (input is RepoInput.Title){
                (binding as LayoutRvHeaderBinding).tvRvHeader.text = input.data.title
            }
        }
    }

    class RepoListViewHolder(binding: LayoutGithubRepoBinding) : GithubViewHolder(binding){
        override fun onBind(input: RepoInput) {
            if(input is RepoInput.RepoList){
                with(binding as LayoutGithubRepoBinding){
                    //이미지 추가
//                    imgGithubRepo.loadCircleImage(input.data.image)
                    tvGithubRepoName.text = input.data.name
                    tvGithubRepoAuthor.text = input.data.author
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return when (viewType){
            HEADER -> {
                val binding = LayoutRvHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = LayoutGithubRepoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                RepoListViewHolder(binding)
            }
        }
    }
    override fun getItemCount(): Int = currentList.size + 1

    private fun RepoData.Title.toInput() = RepoInput.Title(this)
    private fun RepoData.RepoInfo.toInput() = RepoInput.RepoList(this)

    sealed class RepoInput {
        data class Title(val data: RepoData.Title) : RepoInput()
        data class RepoList(val data: RepoData.RepoInfo) : RepoInput()
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.onBind((if (position == 0) title.toInput() else getItem(position - 1).toInput()))
    }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when(holder){
//            is HeaderViewHolder -> holder.onBind(RepoListAdapter.RepoInput)
//            is RepoListViewHolder -> holder.onBind()
//        }
//    }
//
//    fun setRepoList(repoList: List<RepoData>) {
//        this.repoList = repoList.toList()
//        notifyDataSetChanged()
//    }

    private class DiffutilCallback : DiffUtil.ItemCallback<RepoData.RepoInfo>() {
        override fun areItemsTheSame(
            oldItem: RepoData.RepoInfo,
            newItem: RepoData.RepoInfo
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: RepoData.RepoInfo,
            newItem: RepoData.RepoInfo
        ) = oldItem == newItem
    }

}
