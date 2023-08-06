package com.apoplawski.codesamples.navigation.model

sealed class NavigationCommand {

    object NavigateBack : NavigationCommand()

    class Navigate(
        val destination: NavigationDestination,
        val singleTop: Boolean,
    ) : NavigationCommand()

    class PopUpTo(
        val destination: NavigationDestination,
        val inclusive: Boolean,
        val saveState: Boolean,
    ) : NavigationCommand()

    class SwitchBackstack(
        val rootDestination: NavigationDestination,
    ) : NavigationCommand()

    val route: String
        get() = when (this) {
            is NavigateBack -> ""
            is Navigate -> this.destination.route
            is PopUpTo -> this.destination.route
            is SwitchBackstack -> this.rootDestination.route
        }

    val genericRoute: String
        get() = when (this) {
            is NavigateBack -> ""
            is Navigate -> this.destination.genericRoute
            is PopUpTo -> this.destination.genericRoute
            is SwitchBackstack -> this.rootDestination.genericRoute
        }
}
