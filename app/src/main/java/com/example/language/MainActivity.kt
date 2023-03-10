package com.example.language

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.viewpager.widget.ViewPager
import com.example.language.databinding.ActivityMainBinding
import com.example.language.navigation.MainScreen
import com.example.language.navigation.MainScreenNavigation
import com.example.language.ui.theme.LanguageTheme
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : ComponentActivity() {

    private lateinit var binding : ActivityMainBinding

    @OptIn(
        ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LanguageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenNavigation()
                }
            }
        }

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        // Set the content of the activity to use the activity_main.xml layout file
//        setContentView(view)
//
//        // Find the view pager that will allow the user to swipe between fragments
//        val viewPager = findViewById<ViewPager>(R.id.pager)
//
//        // Create an adapter that knows which fragment should be shown on each page
//        val adapter = CategoryAdapter(this, supportFragmentManager)
//
//        // Set the adapter onto the view pager
//        viewPager.adapter = adapter
//
//        // Find the tab layout that shows the tabs
//        val tabLayout = findViewById<TabLayout>(R.id.tabs)
//
//        // Connect the tab layout with the view pager. This will
//        //   1. Update the tab layout when the view pager is swiped
//        //   2. Update the view pager when a tab is selected
//        //   3. Set the tab layout's tab names with the view pager's adapter's titles
//        //      by calling onPageTitle()
//        tabLayout.setupWithViewPager(binding.pager)


    }

}

