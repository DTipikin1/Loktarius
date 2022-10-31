package com.loktarius.presentation.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object AddEditTagScreen: Screen("add_edit_tag_screen")
}
