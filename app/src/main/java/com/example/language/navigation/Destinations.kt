package com.example.language.navigation

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object ForgetPassword : AuthScreen("forgetPassword")
    object AuthType : AuthScreen("authType")
    object UserType : AuthScreen("userType")
    object UserInfo : AuthScreen("userInfo")
}

sealed class MainScreen(val route: String, val title: String,) {
    object Admin : MainScreen("admin", "Admin",)
    object Contractor : MainScreen("contractor", "Contractor",)

    object ProfileEdit : MainScreen("edit", "Edit",)

}


//sealed class BottomBar(val route: String, val title: String, val icon: ImageVector) {
//    object PickUp : BottomBar("pickup", "PickUp", Icons.Default.DeliveryDining)
//    object Profile : BottomBar("profile", "Profile", Icons.Default.Person)
//    object Camera : BottomBar("camera", "Camera", Icons.Filled.CameraAlt)
//}