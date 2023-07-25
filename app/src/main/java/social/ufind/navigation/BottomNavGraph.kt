package social.ufind.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import social.ufind.firebase.model.User
import social.ufind.ui.screen.home.savedpost.SavedPostScreen
import social.ufind.ui.screen.home.user.UserProfileScreen
import social.ufind.ui.screen.home.post.PostScreen
import social.ufind.ui.screen.home.post.add.AddPostScreen
import social.ufind.ui.screen.home.post.add.map.MapScreen


import social.ufind.ui.screen.home.user.settings.SettingsAccountScreen
import social.ufind.ui.screen.home.user.settings.SettingsChangePassword
import social.ufind.ui.screen.home.user.settings.SettingsPreferencesScreen
import social.ufind.ui.screen.home.user.settings.SettingsScreen
import social.ufind.ui.screen.home.user.settings.SettingsSecurityScreen
import social.ufind.ui.screen.home.user.wallet.WalletProfileScreen


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route ){

        composable(OptionsRoutes.ChatScreen2.route) { navBackStackEntry ->
            // Creating gson object
            val gson: Gson = GsonBuilder().create()
            /* Extracting the user object json from the route */
            val userJson = navBackStackEntry.arguments?.getString("user")
            // Convert json string to the User data class object
            val userObject = gson.fromJson(userJson, User::class.java)

        }

            PostScreen.composable(this, navController)
        AddPostScreen.composable(this, navController)
        SavedPostScreen.composable(this, navController)
        UserProfileScreen.composable(this, navController)
        SettingsScreen.composable(this, navController)


        composable(route = OptionsRoutes.SettingsPreferencesScreen.route) {
            SettingsPreferencesScreen { navController.navigate(OptionsRoutes.SettingsScreen.route) }
        }
        composable(route = OptionsRoutes.SettingsSecurityScreen.route) {
            SettingsSecurityScreen(
                onClickSettingsScreen = { navController.navigate(OptionsRoutes.SettingsScreen.route) }
            ) {
                navController.navigate(
                    OptionsRoutes.SettingsChangePassword.route
                )
            }
        }
        composable(route = OptionsRoutes.SettingsAccountScreen.route) {
            SettingsAccountScreen { navController.navigate(OptionsRoutes.SettingsScreen.route) }
        }
        composable(route = OptionsRoutes.SettingsChangePassword.route) {
            SettingsChangePassword(
                onClickBackToSettings = { navController.navigate(OptionsRoutes.SettingsScreen.route) },
                onClickSettingsSecurityScreen = { navController.navigate(OptionsRoutes.SettingsSecurityScreen.route) })
        }
        composable(route = OptionsRoutes.WalletScreen.route) {
            WalletProfileScreen()
        }
        composable(route = OptionsRoutes.MapScreen.route){
            MapScreen { navController.navigate(OptionsRoutes.AddPostScreen.route) }
        }


    }
}