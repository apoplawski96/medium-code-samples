package com.apoplawski.codesamples.navigation.model

object Destinations {

    object WelcomeScreen : NavigationDestination {
        override val route: String
            get() = "destination_welcome_screen"
        override val genericRoute: String
            get() = "destination_welcome_screen"
        override val arguments: List<NavigationArgument>
            get() = emptyList()
    }

    object SearchScreen : NavigationDestination {
        override val route: String
            get() = "search_screen"
        override val genericRoute: String
            get() = "search_screen"
        override val arguments: List<NavigationArgument>
            get() = emptyList()
    }

//    object Menu : NavigationDestination {
//
//        override val route: String = "destination_menu"
//        override val genericRoute: String = "destination_menu"
//        override val arguments: List<NavigationArgument> = listOf()
//    }
//
//    object QuestionsQuiz : NavigationDestination {
//
//        override val route: String
//            get() = "destination_quiz"
//        override val genericRoute: String
//            get() = "destination_quiz"
//        override val arguments: List<NavigationArgument>
//            get() = listOf()
//    }
//
//    object QuestionsList : NavigationDestination {
//
//        const val topCategoryIdArg: String = "top_category"
//        const val subCategoryIdArg: String = "sub_category"
//
//        override val route: String
//            get() = "destination_list/{$topCategoryIdArg}/{$subCategoryIdArg}"
//        override val genericRoute: String
//            get() = "destination_list"
//        override val arguments: List<NavigationArgument>
//            get() = listOf(
//                NavigationArgument.StringArgument(key = topCategoryIdArg),
//                NavigationArgument.StringArgument(key = subCategoryIdArg, nullable = true)
//            )
//
//        fun destination(topCategoryId: String, subCategoryId: String?) = navigationDestinationOf(
//            route = "$genericRoute/$topCategoryId/$subCategoryId",
//            genericRoute = genericRoute,
//            arguments = arguments
//        )
//    }
//
//    object Dogs : NavigationDestination {
//
//        override val route: String
//            get() = "destination_dogs"
//        override val genericRoute: String
//            get() = "destination_dogs"
//        override val arguments: List<NavigationArgument>
//            get() = listOf()
//    }
//
//    object Categories : NavigationDestination {
//
//        override val route: String
//            get() = "destination_categories"
//        override val genericRoute: String
//            get() = "destination_categories"
//        override val arguments: List<NavigationArgument>
//            get() = listOf()
//    }
//
//    object SubCategories : NavigationDestination {
//
//        const val categoryIdArgName = "categoryId"
//
//        override val route: String
//            get() = "destination_sub_categories/{categoryId}"
//        override val genericRoute: String
//            get() = "destination_sub_categories"
//        override val arguments: List<NavigationArgument>
//            get() = listOf(NavigationArgument.StringArgument(key = "categoryId", argument = ""))
//
//        fun destination(categoryId: String)  = navigationDestinationOf(
//            route = "$genericRoute/$categoryId",
//            genericRoute = genericRoute,
//            arguments = arguments
//        )
//    }
}