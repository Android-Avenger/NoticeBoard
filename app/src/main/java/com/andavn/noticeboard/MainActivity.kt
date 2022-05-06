package com.andavn.noticeboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import com.andavn.noticeboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = hostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.navigation)
        navGraph.setStartDestination(R.id.home2)
        navController.graph = navGraph
    }
}
