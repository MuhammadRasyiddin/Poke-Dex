package com.example.pokedex.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.ui.main.fragment.about.AboutFragment
import com.example.pokedex.ui.main.fragment.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.template_toolbar.*

class MainActivity : AppCompatActivity() {
    private var mCurrentFragment: Fragment? = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mCurrentFragment!!)
                .commit()
        } else {
            mCurrentFragment = supportFragmentManager.getFragment(savedInstanceState, "FRAGMENT")
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, mCurrentFragment!!)
                .commit()

        }

        setUp()
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    mCurrentFragment = HomeFragment()

                }
                R.id.about -> {
                    mCurrentFragment =
                        AboutFragment()
                }

            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mCurrentFragment!!)
                .commit()
            true
        }
    }
}
