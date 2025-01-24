package com.multimodule.navigator.destination

const val SIGNUP_ROUTE = "SignUpRoute"
class SignUpDestination : NavigationDestination {
    override fun destination(): String = Screens.SignUpScreenRoute.route
}