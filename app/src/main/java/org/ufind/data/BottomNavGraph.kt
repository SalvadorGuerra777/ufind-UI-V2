package org.ufind.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.ufind.ui.screen.settings.SettingsScreen
import org.ufind.ui.screen.userhomescreen.HomeMenuScreen
import org.ufind.ui.screen.userhomescreen.MainChatScreen
import org.ufind.ui.screen.userhomescreen.SavedPostScreen
import org.ufind.ui.screen.userhomescreen.UserProfileScreen
import org.ufind.ui.screen.userpost.addpost.ui.AddPostScreen
import org.ufind.ui.screen.userpost.addpost.ui.PostScreen
import org.ufind.ui.screen.wallet.WalletProfileScreen


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {

        composable(route = BottomBarScreen.Home.route) {
            HomeMenuScreen { navController.navigate(OptionsRoutes.AddPostScreen.route) }
        }
        composable(route = BottomBarScreen.Profile.route) {
            UserProfileScreen(
                onClickProfileSettings = { navController.navigate(OptionsRoutes.SettingsScreen.route) },
                onClickWalletButton = { navController.navigate(OptionsRoutes.WalletScreen.route) }
            )
        }

        composable(route = BottomBarScreen.SavedPosts.route) {
            SavedPostScreen()
        }

        composable(route = BottomBarScreen.Chat.route) {
            MainChatScreen()
        }

        composable(route = OptionsRoutes.AddPostScreen.route) {
            AddPostScreen { navController.navigate(BottomBarScreen.Home.route) }
        }
        composable(route = OptionsRoutes.PostScreen.route) {
            PostScreen { navController.navigate(OptionsRoutes.AddPostScreen.route) }
        }
        composable(route = OptionsRoutes.SettingsScreen.route){
            SettingsScreen()
        }
        composable(route = OptionsRoutes.WalletScreen.route){
            WalletProfileScreen()
        }

    }
}