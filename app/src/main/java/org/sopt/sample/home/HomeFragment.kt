package org.sopt.sample.home

import android.app.Service
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.sopt.sample.data.remote.ReqresFollowerService
import org.sopt.sample.data.remote.ResponseFollowerListDTO
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.home.adapter.RfollowersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 초기화 에러" }

    private val followersViewModel by viewModels<FollowersViewModel>()
    private lateinit var followerListAdapter: RfollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFollowerList()
    }

    private fun setFollowerList() {
        val setfollowerSerive = ServicePool.reqresFollowerService

        setfollowerSerive
            .getFollower(1)//여기서 페이지 수 기준으로 받아서 일단 1로 받았는데 이래도 되는걸까
            .enqueue(object : Callback<ResponseFollowerListDTO>{
            override fun onResponse(
                call: Call<ResponseFollowerListDTO>,
                response: Response<ResponseFollowerListDTO>
            ) {
                if(response.isSuccessful){
                    Log.e("reqres 서버통신 성공", "followers success")
                    followerListAdapter = RfollowersAdapter(requireContext())
                    binding.rvFollowers.adapter = followerListAdapter
                }
            }

            override fun onFailure(call: Call<ResponseFollowerListDTO>, t: Throwable) {
                Log.e("reqres 서버통신 실패", "followers failed")
            }
        })
    }
}
