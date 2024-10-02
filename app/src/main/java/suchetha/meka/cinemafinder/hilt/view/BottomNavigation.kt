package suchetha.meka.cinemafinder.hilt.view

import suchetha.meka.cinemafinder.R

sealed class BottomNavigation(val title: String, val icon: Int, val route: String) {
    data object Home : BottomNavigation("Home", R.drawable.baseline_add_home_24, "home")
    data object Favorite : BottomNavigation("Favorite", R.drawable.baseline_favorite_24, "favorite")
    data object Profile :
        BottomNavigation("Profile", R.drawable.baseline_emoji_people_24, "profile")
}