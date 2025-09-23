package com.project.ailarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.project.ailarm.model.AlarmItem
import com.project.ailarm.ui.screens.AlarmListScreen
import com.project.ailarm.ui.theme.AilarmTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.ailarm.ui.screens.AlarmFormScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            AilarmTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()
    val alarms = listOf(
        AlarmItem("07:00 a.m.", listOf("Diaria", "Despertar")),
        AlarmItem("07:15 a.m.", listOf("Diaria", "Medicamento")),
        AlarmItem("05:00 p.m.", listOf("L, M, X, V", "Ejercicio")),
    )

    NavHost(
        navController = navController,
        startDestination = "/alarms"
    ) {
        composable(
            "/alarms?showSnackbar={showSnackbar}",
            arguments = listOf(
                navArgument("showSnackbar") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { backStackEntry ->
            val showSnackbar = backStackEntry.arguments?.getBoolean("showSnackbar") ?: false

            AlarmListScreen(
                alarms,
                onAddAlarm = {
                    navController.navigate("/alarm-form")
                },
                showSnackbar
            )
        }
        composable("/alarm-form",) {
            AlarmFormScreen(
                onClickBackBtn = {
                    navController.navigate("/alarms")
                },
                onClickSaveBtn = {
                    navController.navigate("/alarms?showSnackbar=true") {
                        launchSingleTop = true
                        popUpTo("/alarms") { inclusive = true }
                    }
                }
            )
        }
    }
}
