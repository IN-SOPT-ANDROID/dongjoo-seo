package org.sopt.sample.presentation.myPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment(){
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "바인딩 초기화 에러" }

    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.imgMyPage.load(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetImage.setOnClickListener{
            launcher.launch("image/*")
        }

    }
}