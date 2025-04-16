package com.unifil.loventure.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unifil.loventure.ui.screen.LoginScreen
import com.unifil.loventure.ui.screen.NpcListScreen
import com.unifil.loventure.ui.screen.ChatScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.LOGIN) {
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController)
        }
        composable(NavRoutes.NPC_LIST) {
            NpcListScreen(navController)
        }
        composable(NavRoutes.CHAT) {
            ChatScreen(navController)
        }
    }
}