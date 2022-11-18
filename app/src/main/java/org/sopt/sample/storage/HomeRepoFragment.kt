//package org.sopt.sample.storage
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import org.sopt.sample.databinding.FragmentHomeBinding
//import org.sopt.sample.home.ViewModel
//import org.sopt.sample.home.adapter.RepoListAdapter
//import org.sopt.sample.home.data.RepoData
//
///*
//* onCreateView : View 생성 로직
//* onViewCreated : 생성된 뷰 구조 활용해서 Fragment에 사용자 인터렉션 구현하는 구현부
//* -> onViewCreated에서 binding 객체를 활용하여 뷰의 UI 구현
//* */
//
//class HomeRepoFragment : Fragment() {
//    private val viewModel by viewModels<ViewModel>()
//    //ViewBinding은 onCreateView에서 생성 후 on DestroyView에서 null 로 직접 해제(Fragment 생명주기가 View 생명주기보다 길다.)
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = requireNotNull(_binding) { "바인딩 초기화 에러 발생" }
//
//    private lateinit var repoListAdapter: RepoListAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentHomeBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        repoListAdapter = RepoListAdapter(RepoData.Title("tamazzang"))
//        binding.rvRepos.adapter = repoListAdapter
//        repoListAdapter.submitList(viewModel.mockRepoList)
//}
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        fun newInstance(): HomeRepoFragment {
//            return HomeRepoFragment()
//        }
//    }
//}