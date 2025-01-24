package com.multimodule.navigator.destination

const val SIGNUP_ROUTE = "SignUpRoute"
class SignUpDestination : NavigationDestination {
    override fun route(): String = Screens.SignUpScreenRoute.route
}