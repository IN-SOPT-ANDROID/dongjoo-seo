package org.sopt.sample.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.home.adapter.RepoAdapter
import org.sopt.sample.home.data.Repo

/*
* onCreateView : View 생성 로직
* onViewCreated : 생성된 뷰 구조 활용해서 Fragment에 사용자 인터렉션 구현하는 구현부
* -> onViewCreated에서 binding 객체를 활용하여 뷰의 UI 구현
* */

class HomeFragment : Fragment() {
    //    ViewBinding은 onCreateView에서 생성 후 on DestroyView에서 null 로 직접 해제(Fragment 생명주기가 View 생명주기보다 길다.)
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 생성하고 써라" }


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
        val adapter = RepoAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(mockRepoList)
    }

    private val mockRepoList = listOf<Repo>(
        Repo(
            image = R.drawable.github,
            name = "A",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "B",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "C",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "D",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "E",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "F",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "G",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "H",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "I",
            author = "123"
        ),
        Repo(
            image = R.drawable.github,
            name = "J",
            author = "123"
        )

    )
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}