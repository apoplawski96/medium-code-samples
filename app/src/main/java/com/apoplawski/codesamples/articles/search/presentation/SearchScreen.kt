@file:OptIn(ExperimentalComposeUiApi::class)

package com.apoplawski.codesamples.articles.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apoplawski.codesamples.R
import com.apoplawski.codesamples.articles.search.presentation.components.SearchInputField
import com.apoplawski.codesamples.articles.search.domain.model.SearchResult
import com.apoplawski.codesamples.articles.search.presentation.components.Keyboard
import com.apoplawski.codesamples.articles.search.presentation.components.keyboardAsState
import com.apoplawski.codesamples.ui.theme.color_dark_blue
import com.apoplawski.codesamples.ui.theme.color_silver
import com.apoplawski.codesamples.ui.theme.color_soft_white
import com.apoplawski.codesamples.ui.theme.fonts
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = getViewModel()) {

    val viewState = viewModel.viewState.collectAsState().value
    val searchFieldState = viewModel.searchFieldState.collectAsState().value
    val inputText = viewModel.inputText.collectAsState().value

    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardState by keyboardAsState()

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    SearchScreenLayout(
        viewState = viewState,
        searchFieldState = searchFieldState,
        inputText = inputText,
        onSearchInputChanged = { input -> viewModel.updateInput(input) },
        onSearchInputClicked = { viewModel.searchFieldActivated() },
        onClearInputClicked = { viewModel.clearInput() },
        onChevronClicked = {
            viewModel.revertToInitialState()
            keyboardController?.hide()
        },
        keyboardState = keyboardState,
        onKeyboardHidden = { viewModel.keyboardHidden() },
        onItemClicked = {
//            viewModel.onItemCLicked()
        }
    )
}

@Composable
private fun SearchScreenLayout(
    viewState: SearchViewModel.ViewState,
    searchFieldState: SearchViewModel.SearchFieldState,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchInputClicked: () -> Unit,
    onClearInputClicked: () -> Unit,
    onChevronClicked: () -> Unit,
    onKeyboardHidden: () -> Unit,
    keyboardState: Keyboard,
    onItemClicked: (SearchResult) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_dark_blue)
    ) {
        SearchHeader(searchFieldState = searchFieldState)
        SearchInputField(
            searchFieldState = searchFieldState,
            inputText = inputText,
            onClearInputClicked = onClearInputClicked,
            onSearchInputChanged = onSearchInputChanged,
            onClicked = onSearchInputClicked,
            onChevronClicked = onChevronClicked,
            onKeyboardHidden = onKeyboardHidden,
            keyboardState = keyboardState,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color_silver.copy(alpha = 0.2f))
        )
        when (viewState) {
            SearchViewModel.ViewState.IdleScreen -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.undraw_search),
                        contentDescription = "Illustration",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            SearchViewModel.ViewState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error :(", color = color_soft_white)
                }
            }

            SearchViewModel.ViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            SearchViewModel.ViewState.NoResults -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No results for this input :(", color = color_soft_white)
                }
            }

            is SearchViewModel.ViewState.SearchResultsFetched -> {
                SearchResultsList(items = viewState.results, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
private fun SearchHeader(
    searchFieldState: SearchViewModel.SearchFieldState
) {
    AnimatedVisibility(visible = searchFieldState == SearchViewModel.SearchFieldState.Idle) {
        Text(
            text = "Search",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp),
            style = TextStyle(
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                fontFamily = fonts,
                color = color_soft_white
            )
        )
    }
}

@Composable
private fun SearchResultsList(items: List<SearchResult>, onItemClicked: (SearchResult) -> Unit) {
    LazyColumn {
        itemsIndexed(items = items) { index, searchResult ->
            Column(modifier = Modifier.fillMaxWidth().clickable { onItemClicked.invoke(searchResult) }) {
                Spacer(
                    modifier = Modifier.height(height = if (index == 0) 16.dp else 4.dp)
                )
                Text(
                    text = searchResult.title,
                    color = color_soft_white,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = searchResult.subtitle,
                    color = color_silver,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 16.dp)
                        .background(color_silver.copy(alpha = 0.2f))
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}