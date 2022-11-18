package org.sopt.sample.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.R
import org.sopt.sample.data.remote.ResponseFollowerListDTO
import org.sopt.sample.databinding.LayoutFollowerGridBinding
import org.sopt.sample.databinding.LayoutRvHeaderBinding

class RfollowersAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val inflater by lazy { LayoutInflater.from(context) }
    private var FollowerListData = mutableListOf<ResponseFollowerListDTO.FollowerData>()

    class FollowerViewHolder(private val binding : LayoutFollowerGridBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(input : ResponseFollowerListDTO.FollowerData){
            with(binding as LayoutFollowerGridBinding){
                //이름 전체 출력 방안 고민해보기
                tvRfollowerName.text = input.firstName
                tvRfollowerEmail.text = input.email
            }
            Glide.with(this.binding.root)
                .load(input.avatar)
                .circleCrop()
                .into(binding.ivRfollowerProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FollowerViewHolder(LayoutFollowerGridBinding.inflate(inflater, parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position : Int){
        if (holder is FollowerViewHolder){
            holder.onBind(FollowerListData[position])
        }
    }

    override fun getItemCount(): Int {
        return FollowerListData.size
    }

}