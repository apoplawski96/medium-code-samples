package com.apoplawski.codesamples.articles.search.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.apoplawski.codesamples.R
import com.apoplawski.codesamples.articles.search.presentation.NewSearchViewModel
import com.apoplawski.codesamples.ui.theme.color_blue
import com.apoplawski.codesamples.ui.theme.color_cyan
import com.apoplawski.codesamples.ui.theme.color_silver
import com.apoplawski.codesamples.ui.theme.color_soft_white
import com.apoplawski.codesamples.ui.theme.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInputField(
    searchFieldState: NewSearchViewModel.SearchFieldState,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onClearInputClicked: () -> Unit,
    onClicked: () -> Unit,
    onChevronClicked: () -> Unit,
    onKeyboardHidden: () -> Unit,
    keyboardState: Keyboard
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val isKeyboardOpen = keyboardState == Keyboard.Opened

    if (searchFieldState is NewSearchViewModel.SearchFieldState.WithInputActive && isKeyboardOpen) {
        onKeyboardHidden()
    }

    TextField(
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onKeyboardHidden()
        }, onGo = {
            onKeyboardHidden()
        }),
        value = inputText,
        onValueChange = { newInput -> onSearchInputChanged(newInput) },
        leadingIcon = {
            when (searchFieldState) {
                NewSearchViewModel.SearchFieldState.Idle -> MyIcon(
                    resource = R.drawable.navbar_icons_search_inactive,
                    tint = color_blue.copy(alpha = 0.6f),
                    contentDescription = "Search icon",
                )

                NewSearchViewModel.SearchFieldState.EmptyActive,
                NewSearchViewModel.SearchFieldState.WithInputActive -> MyIcon(
                    resource = R.drawable.ic_chevron_left,
                    tint = color_silver.copy(alpha = 0.6f),
                    modifier = Modifier.clickable { onChevronClicked.invoke() },
                    contentDescription = "Search chevron icon"
                )
            }
        },
        colors = when (searchFieldState) {
            NewSearchViewModel.SearchFieldState.Idle -> searchFieldColorsStateIdle()
            NewSearchViewModel.SearchFieldState.EmptyActive,
            NewSearchViewModel.SearchFieldState.WithInputActive -> searchFieldColorsStateActive()
        },
        trailingIcon = if (searchFieldState is NewSearchViewModel.SearchFieldState.WithInputActive) {
            {
                MyIcon(
                    resource = R.drawable.ic_close_search,
                    tint = color_silver,
                    modifier = Modifier.clickable { onClearInputClicked.invoke() },
                    contentDescription = "Search close icon",
                )
            }
        } else {
            null
        },
        placeholder = {
            MyText(
                text = "What are you searching for?",
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    letterSpacing = TextUnit(-0.01f, TextUnitType.Sp),
                    fontFamily = fonts,
                    color = when (searchFieldState) {
                        NewSearchViewModel.SearchFieldState.EmptyActive -> color_silver.copy(alpha = 0.6f)
                        NewSearchViewModel.SearchFieldState.Idle -> color_blue.copy(alpha = 0.6f)
                        is NewSearchViewModel.SearchFieldState.WithInputActive -> color_silver.copy(
                            alpha = 0.6f
                        )
                    },
                )
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .focusRequester(focusRequester)
            .focusable(true),
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(key1 = interactionSource) {
                interactionSource.interactions.collect { interaction ->
                    if (interaction is PressInteraction.Release) {
                        onClicked.invoke()
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun searchFieldColorsStateIdle() = TextFieldDefaults.textFieldColors(
    containerColor = color_soft_white,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    textColor = color_blue,
    disabledTextColor = color_blue.copy(alpha = 0.6f),
    cursorColor = Color.Transparent,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun searchFieldColorsStateActive() = TextFieldDefaults.textFieldColors(
    containerColor = color_blue,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    textColor = color_soft_white,
    disabledTextColor = color_soft_white,
    cursorColor = color_cyan,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
)