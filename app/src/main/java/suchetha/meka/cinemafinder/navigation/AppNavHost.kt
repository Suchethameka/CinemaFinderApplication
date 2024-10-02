package suchetha.meka.cinemafinder.navigation

import suchetha.meka.cinemafinder.hilt.view.WelcomeScreen
import suchetha.meka.cinemafinder.hilt.view.screens.FavoritesScreenUI
import suchetha.meka.cinemafinder.hilt.view.screens.MovieDetailsScreen
import suchetha.meka.cinemafinder.hilt.view.screens.NowPlayingScreenUI
import suchetha.meka.cinemafinder.hilt.view.screens.PopularScreenUI
import suchetha.meka.cinemafinder.hilt.view.screens.TopRatedScreenUI
import suchetha.meka.cinemafinder.hilt.view.screens.UpcomingScreenUI
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = NavigationItem.WELCOME.route
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.WELCOME.route) {
            WelcomeScreen(navHostController)
        }
        composable(NavigationItem.FAVORITE_SCREEN.route) {
            FavoritesScreenUI(navHostController)
        }
        composable(NavigationItem.POPULARLISTSCREEN.route) {
            PopularScreenUI(hiltViewModel(), navHostController)
        }

        composable(NavigationItem.NOWPLAYINGLISTSCREEN.route) {
            NowPlayingScreenUI(hiltViewModel(), navHostController)
        }
        composable(NavigationItem.UPCOMINGLISTSCREEN.route) {
            UpcomingScreenUI(hiltViewModel(), navHostController)
        }
        composable(
            NavigationItem.TOPRATEDLISTSCREEN.route
        ) {
            TopRatedScreenUI(hiltViewModel(), navHostController)
        }
        composable(
            "${NavigationItem.DETAILS_SCREEN.route}/{movieId}"
        )
        { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            MovieDetailsScreen(navHostController, hiltViewModel(), movieId)
        }
    }
}