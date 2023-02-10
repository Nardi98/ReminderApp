package com.example.reminderapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reminderapp.ui.Login.Login
import com.example.reminderapp.ui.account.AccountPage
import com.example.reminderapp.ui.home.Home
import com.example.reminderapp.ui.newReminder.NewReminder

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderApp( appState: ReminderAppState = rememberReminderAppState()) {

    NavHost(
        navController = appState.navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            Login(navController = appState.navController)
        }
        composable(route = "home") {
            Home(navController = appState.navController)
        }
        composable(route = "account"){
            AccountPage(navController = appState.navController)
        }
        composable(route = "newReminder"){
            NewReminder(navController = appState.navController)
        }

        /*composable(route = "payment") {
            Payment(onBackPress = appState::navigateBack)
        }*/
    }
}

class ReminderAppState(
    val navController: NavHostController
) {
    fun navigateBack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberReminderAppState(navController: NavHostController = rememberNavController()) = remember(navController) {
    ReminderAppState(navController)
}