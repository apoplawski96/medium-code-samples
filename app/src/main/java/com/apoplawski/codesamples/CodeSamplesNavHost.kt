package com.apoplawski.codesamples

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apoplawski.codesamples.navigation.Navigator
import com.apoplawski.codesamples.navigation.model.Destinations
import com.apoplawski.codesamples.navigation.model.NavigationCommand
import com.apoplawski.codesamples.screens.search.SearchScreen
import org.koin.androidx.compose.get

@Composable
fun CodeSamplesNavHost(navigator: Navigator = get()) {

    val navController = rememberNavController()

    LaunchedEffect(null) {
        navigator.commands.collect { command ->
            handleNavigationCommand(command = command, navController = navController)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Destinations.SearchScreen.route
    ) {
        welcomeScreen()
        searchScreen()
    }
}

private fun handleNavigationCommand(
    command: NavigationCommand,
    navController: NavController,
) {
    if (command.route.isEmpty() && command !is NavigationCommand.NavigateBack) return

    when (command) {
        is NavigationCommand.NavigateBack -> {
            navController.popBackStack()
        }

        is NavigationCommand.Navigate -> {
            navController.navigate(route = command.route) {
                launchSingleTop = command.singleTop
            }
        }

        is NavigationCommand.PopUpTo -> {
            navController.popBackStack(
                route = command.genericRoute,
                inclusive = command.inclusive,
                saveState = command.saveState
            )
        }

        is NavigationCommand.SwitchBackstack -> {
            navController.navigate(route = command.route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }
}

private fun NavGraphBuilder.welcomeScreen() {
    composable(route = Destinations.WelcomeScreen.route) {
        // TODO
    }
}

private fun NavGraphBuilder.searchScreen() {
    composable(route = Destinations.SearchScreen.route) {
        SearchScreen()
    }
}