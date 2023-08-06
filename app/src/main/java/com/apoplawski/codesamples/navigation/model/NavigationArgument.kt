package com.apoplawski.codesamples.navigation.model

sealed class NavigationArgument(open val key: String, open val nullable: Boolean) {
    data class StringArgument(
        override val key: String,
        val argument: String = "",
        override val nullable: Boolean = false
    ) : NavigationArgument(key = key, nullable = nullable)

    data class IntArgument(
        override val key: String,
        val argument: Int = -1,
        override val nullable: Boolean = false
    ) : NavigationArgument(key = key, nullable = nullable)
}