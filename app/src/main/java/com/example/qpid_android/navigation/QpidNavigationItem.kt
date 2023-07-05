package com.example.qpid_android.navigation

sealed class QpidNavigationItem(val route: String) {

    object Splash : QpidNavigationItem("splash")

    object Main : QpidNavigationItem("main")

    object Write : QpidNavigationItem("write")

    object Signin : QpidNavigationItem("signin")

    object Signup : QpidNavigationItem("signup")

}
