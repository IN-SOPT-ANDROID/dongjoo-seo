package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.home.GalleryFragment
import org.sopt.sample.home.HomeFragment
import org.sopt.sample.home.SearchFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val homeFragment by lazy { HomeFragment() }
    private val galleryFragment by lazy { GalleryFragment() }
    private val searchFragment by lazy { SearchFragment() }

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
                    replaceFragment(searchFragment)
                }
            }
            true
        }
        binding.bnvHome.selectedItemId = R.id.menu_home
    }
}
