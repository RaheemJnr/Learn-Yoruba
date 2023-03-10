package com.example.language.navigation

sealed class MainScreen(val route : String, val title : String) {
    object Numbers : MainScreen("number", "Numbers")
    object Family : MainScreen("family", "Family")

    object Colors : MainScreen("colors", "Colors")
    object Phrases : MainScreen("phrases", "Phrases")

}