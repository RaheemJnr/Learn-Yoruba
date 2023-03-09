package com.example.language.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.evapingnow.woodchuck.repository.local.LocalDataStorage
import com.evapingnow.woodchuck.repository.remote.AuthRepoImpl
import com.evapingnow.woodchuck.repository.remote.MainRepoImpl
import com.evapingnow.woodchuck.ui.screens.authScreen.forgetPassword.ForgetPasswordScreen
import com.evapingnow.woodchuck.ui.screens.authScreen.login.LoginMainScreen
import com.evapingnow.woodchuck.ui.screens.authScreen.register.AuthTypeScreen
import com.evapingnow.woodchuck.ui.screens.authScreen.register.UserInfoScreen
import com.evapingnow.woodchuck.ui.screens.authScreen.register.UserTypeScreen
import com.evapingnow.woodchuck.ui.screens.mainScreen.admin.AdminScreen
import com.evapingnow.woodchuck.ui.screens.mainScreen.camera.CameraContent
import com.evapingnow.woodchuck.ui.screens.mainScreen.contractor.ContractorScreen
import com.evapingnow.woodchuck.ui.screens.mainScreen.edit.EditScreen
import com.evapingnow.woodchuck.ui.screens.mainScreen.pickup.PickupScreen
import com.evapingnow.woodchuck.ui.screens.mainScreen.profile.ProfileScreen
import com.evapingnow.woodchuck.ui.viewmodels.AuthViewModel
import com.evapingnow.woodchuck.ui.viewmodels.AuthViewModelFactory
import com.evapingnow.woodchuck.ui.viewmodels.MainViewModel
import com.evapingnow.woodchuck.ui.viewmodels.MainViewModelFactory
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalAnimationApi
@Composable
fun AuthScreenNavigation() {

    /***
     * Before you criticize me! I know all these are to be placed in a DI module but we'll get there
     * */
    val navController = rememberNavController()
    val context = LocalContext.current
    val sessionManger = LocalDataStorage(context = context)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(repo = AuthRepoImpl(sessionManager = sessionManger))
    )
    NavHost(navController, startDestination = AuthScreen.AuthType.route) {
        //Auth Type
        composable(AuthScreen.AuthType.route) {
            AuthTypeScreen(navController)
        }
        //User type
        composable(AuthScreen.UserType.route) {
            UserTypeScreen(navController)
        }
        //user info
        composable(
            "userInfo/{userType}",
            arguments = listOf(navArgument("userType") { defaultValue = "Homeowner" })
        ) {
            UserInfoScreen(
                navController = navController,
                authViewModel = authViewModel,
                user_type = it.arguments?.getString("userType") ?: ""
            )
        }
        //login
        composable(AuthScreen.Login.route) {
            LoginMainScreen(navController, authViewModel = authViewModel)
        }

        //forgetPassword
        composable(AuthScreen.ForgetPassword.route) {
            ForgetPasswordScreen(navController)
        }
    }
}


@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainScreenNavigation(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>
) {

    /**
     * Before you criticize me! I know all these are to be placed in a DI module but we'll get there
     * */
    val context = LocalContext.current
    val localDataStorage = LocalDataStorage(context)

    /** main viewModel   */
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(
            MainRepoImpl(sessionManager = localDataStorage),
            localDataStorage = localDataStorage
        )
    )


    NavHost(navController, startDestination = BottomBar.Profile.route) {
        //profile
        composable(BottomBar.Profile.route) {

            bottomBarState.value = true
            ProfileScreen(
                localDataStorage = localDataStorage,
                navController = navController
            )
        }
        //pickUp
        composable(BottomBar.PickUp.route) {
            bottomBarState.value = true
            PickupScreen()
        }

        //camera
        composable(BottomBar.Camera.route) {
            bottomBarState.value = false
            CameraContent(
                navController = navController,
                viewModel = mainViewModel,
                localStorage = localDataStorage
            )
        }

        //admin section
        composable(MainScreen.Admin.route) {
            bottomBarState.value = false
            AdminScreen(navController, mainViewModel = mainViewModel, context)

        }
        //screen for contractors to add details of their company
        composable(MainScreen.Contractor.route) {
            bottomBarState.value = false
            ContractorScreen(navController, mainViewModel = mainViewModel)

        }

        //screen for editting use profile details
        composable(MainScreen.ProfileEdit.route) {
            bottomBarState.value = false
            EditScreen()

        }

    }

}

