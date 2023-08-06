package com.apoplawski.codesamples.navigation.model

interface NavigationDestination {

    val route: String

    val genericRoute: String

    val arguments: List<NavigationArgument>
}

fun navigationDestinationOf(
    route: String,
    genericRoute: String,
    arguments: List<NavigationArgument> = emptyList()
) = object : NavigationDestination {
    override val route: String = route
    override val genericRoute: String = genericRoute
    override val arguments: List<NavigationArgument> = arguments
}
