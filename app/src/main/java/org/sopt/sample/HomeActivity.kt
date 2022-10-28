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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        clickBnv()

//        val currentFragment = supportFragmentManager.findFragmentById(R.id.home_container)
//        if (currentFragment == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.home_container, HomeFragment.newInstance())
//                .commit()
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.home_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun clickBnv() {
        binding.bnvHome.run {
            setOnItemSelectedListener { Item ->
                when (Item.itemId) {
                    R.id.menu_home -> replaceFragment(HomeFragment())
                    R.id.menu_gallery -> replaceFragment(GalleryFragment())
                    R.id.menu_search -> replaceFragment(SearchFragment())
                }
                true
            }
            selectedItemId = R.id.bnv_home
        }
    }
}
