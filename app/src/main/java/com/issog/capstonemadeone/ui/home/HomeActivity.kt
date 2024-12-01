package com.issog.capstonemadeone.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.issog.capstonemadeone.R
import com.issog.capstonemadeone.core.utils.NavigationUtils.safeNavigate
import com.issog.capstonemadeone.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupHostFragment()
        setupBottomNavigation()
    }

    private fun setupHostFragment() {
        navController = Navigation.findNavController(this, R.id.fragment)
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.movie -> {
                    navController.safeNavigate(R.id.movieFragment)
                    true
                }
                R.id.tv_show -> {
                    navController.safeNavigate(R.id.tvShowFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun initView() {
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}