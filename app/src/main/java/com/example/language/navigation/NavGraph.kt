package com.example.language.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.language.ui.screen.LanguageScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainScreenNavigation() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = MainScreen.Numbers.route) {
        //Number
        composable(MainScreen.Numbers.route) {
            LanguageScreen()
        }

    }

}

