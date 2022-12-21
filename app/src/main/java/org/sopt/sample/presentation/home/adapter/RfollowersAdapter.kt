package org.sopt.sample.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.data.model.response.ResponseFollowerListDTO
import org.sopt.sample.databinding.LayoutFollowerGridBinding

class RfollowersAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var FollowerListData = mutableListOf<ResponseFollowerListDTO.FollowerData>()

    class FollowerViewHolder(private val binding: LayoutFollowerGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(input: ResponseFollowerListDTO.FollowerData) {
            with(binding) {
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

    //ViewHolder 생성하는 함수, 최초 생성 횟 수만큼만 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FollowerViewHolder(LayoutFollowerGridBinding.inflate(inflater, parent, false))
    }

    //만들어진 ViewHolder에 데이터를 바인딩하는 함수 (position ; 리스트 상 순서)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowerViewHolder) {
            holder.onBind(FollowerListData[position])
        }
    }

    override fun getItemCount(): Int {
        return FollowerListData.size
    }

}