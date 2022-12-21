package org.sopt.sample.presentation.home.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.remote.ResponseGetMusicDTO
import org.sopt.sample.databinding.LayoutRvMusicBinding

class MusicAdapter(context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var musicList : List<ResponseGetMusicDTO.Music> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutRvMusicBinding.inflate(inflater,parent,false)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}