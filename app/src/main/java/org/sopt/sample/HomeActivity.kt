package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.presentation.home.gallery.GalleryFragment
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.home.music.MusicFragment
import org.sopt.sample.presentation.myPage.MyPageFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val homeFragment by lazy { HomeFragment() }
    private val galleryFragment by lazy { GalleryFragment() }
    private val musicFragment by lazy { MusicFragment() }
    private val myPageFragment by lazy { MyPageFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)

        initClickEvent()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initClickEvent() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(homeFragment)
                }
                R.id.menu_gallery -> {
                    replaceFragment(galleryFragment)
                }
                R.id.menu_search -> {
                    replaceFragment(musicFragment)
                }
                R.id.menu_mypage -> {
                    replaceFragment(myPageFragment)
                }
            }
            true
        }
        binding.bnvHome.selectedItemId = R.id.menu_home
    }
}
