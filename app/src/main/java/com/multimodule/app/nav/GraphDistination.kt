package com.multimodule.app.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.multimodule.home.homeScreen
import com.multimodule.login.presentation.view.LoginScreen
import com.multimodule.navigator.core.AppNavigator
import com.multimodule.navigator.destination.HomeDestination
import com.multimodule.navigator.destination.LoginDestination
import com.multimodule.navigator.destination.NavigationDestination
import com.multimodule.navigator.destination.SignUpDestination
import com.multimodule.signup.SignUpScreen

private val composableDestinations: Map<NavigationDestination, @Composable (AppNavigator, NavHostController) -> Unit> =
    mapOf(
        SignUpDestination() to { _, _ -> SignUpScreen() },
        HomeDestination to { _, navHostController -> homeScreen(navHostController) },
        LoginDestination() to { appNavigator, _ -> LoginScreen(appNavigator = appNavigator) },
    )

fun NavGraphBuilder.addComposableDestinations(
    appNavigator: AppNavigator,
    navHostController: NavHostController,
) {
    composableDestinations.forEach { entry ->
        val destination = entry.key
        composable(
            route = destination.route(),
            arguments = destination.arguments,
            deepLinks = destination.deepLinks,
        ) {
            entry.value(appNavigator, navHostController)
        }
    }
}