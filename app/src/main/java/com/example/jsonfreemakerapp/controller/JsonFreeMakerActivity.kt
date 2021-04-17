package com.example.jsonfreemakerapp.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.jsonfreemakerapp.R
import com.example.jsonfreemakerapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class JsonFreeMakerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        toolbar = supportActionBar!!

        setContentView(binding.root)

        val bottomNavigation: BottomNavigationView = binding.bottomNavigation
        bottomNavigation.selectedItemId = R.id.page_2

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.page_1 -> {
                toolbar.title =  "Post"
                val postFragment = PostFragment.newInstance()
                openFragment(postFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.page_2 -> {
                toolbar.title = "Photos"
                val photosFragment = PhotosFragment.newInstance()
                openFragment(photosFragment)
                return@OnNavigationItemSelectedListener true
            }
            else -> false
        }
    }

    override fun onStart() {
        super.onStart()
        toolbar.title =  "Post"
        val photosFragment = PhotosFragment.newInstance()
        openFragment(photosFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}