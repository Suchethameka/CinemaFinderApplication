package suchetha.meka.cinemafinder.navigation

/**
 * Sealed class representing different navigation destinations in the application.
 * @property route The route associated with the navigation destination.
 */
sealed class NavigationItem(val route: String) {
    /** Represents the welcome screen. */
    data object WELCOME : NavigationItem(Screen.WELCOME.name)

    /** Represents the list screen. */
    data object POPULARLISTSCREEN : NavigationItem(Screen.POPULARLISTSCREEN.name)

    /** Represents the now playing details screen. */
    data object NOWPLAYINGLISTSCREEN : NavigationItem(Screen.NOWPLAYINGLISTSCREEN.name)

    /** Represents the upcoming list screen. */
    data object UPCOMINGLISTSCREEN: NavigationItem(Screen.UPCOMINGLISTSCREEN.name)

    /** Represents the top-rated list screen. */
    data object TOPRATEDLISTSCREEN: NavigationItem(Screen.TOPRATEDLISTSCREEN.name)
    data object DETAILS_SCREEN: NavigationItem(Screen.DETAILS_SCREEN.name)

    data object FAVORITE_SCREEN: NavigationItem(Screen.FAVORITES_SCREEN.name)
}
