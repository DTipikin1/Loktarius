package com.loktarius.feature_activity.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.loktarius.feature_activity.presentation.HomeScreen
import com.loktarius.feature_activity.presentation.add_edit_activity.AddEditTagScreen
import com.loktarius.feature_activity.presentation.util.Screen
import com.loktarius.ui.theme.LoktariusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoktariusTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditTagScreen.route +
                                    "?tagId={tagId}",
                            arguments = listOf(
                                navArgument(
                                    name = "tagId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {

                            AddEditTagScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}





