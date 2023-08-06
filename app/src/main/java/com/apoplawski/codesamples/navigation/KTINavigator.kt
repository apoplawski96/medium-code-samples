package com.apoplawski.codesamples.navigation

import com.apoplawski.codesamples.navigation.model.NavigationCommand
import com.apoplawski.codesamples.navigation.model.NavigationDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class KTINavigator(private val coroutineScope: CoroutineScope) : Navigator {

    override val commands: MutableSharedFlow<NavigationCommand> = MutableSharedFlow()

    override fun navigateBack() {
        emit(NavigationCommand.NavigateBack)
    }

    override fun navigate(
        destination: NavigationDestination,
        singleTop: Boolean,
    ) {
        emit(
            NavigationCommand.Navigate(
                destination = destination,
                singleTop = singleTop,
            )
        )
    }

    override fun popUpTo(
        destination: NavigationDestination,
        inclusive: Boolean,
        saveState: Boolean,
    ) {
        emit(
            NavigationCommand.PopUpTo(
                destination = destination,
                inclusive = inclusive,
                saveState = saveState,
            )
        )
    }

    override fun switchBackstack(rootDestination: NavigationDestination) {
        emit(
            NavigationCommand.SwitchBackstack(
                rootDestination
            )
        )
    }

    private fun emit(command: NavigationCommand) {
        coroutineScope.launch {
            commands.emit(command)
        }
    }
}
