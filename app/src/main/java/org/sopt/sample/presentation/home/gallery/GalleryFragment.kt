package org.sopt.sample.presentation.home.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = requireNotNull(_binding) { "binding이 생성되지 않았습니다." }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }
}