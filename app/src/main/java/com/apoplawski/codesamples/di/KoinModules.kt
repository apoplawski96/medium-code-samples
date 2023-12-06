package com.apoplawski.codesamples.di

import android.app.Application
import android.content.Context
import com.apoplawski.codesamples.navigation.KTINavigator
import com.apoplawski.codesamples.navigation.Navigator
import com.apoplawski.codesamples.articles.permissions.PermissionRequester
import com.apoplawski.codesamples.articles.search.presentation.SearchViewModel
import com.apoplawski.codesamples.articles.search.data.FakeSearchAPI
import com.apoplawski.codesamples.articles.search.domain.GetSearchResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun appModule(application: Application) = module {
    single<Context> { application }
    single<Navigator> {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        KTINavigator(scope)
    }
    singleOf(::PermissionRequester)
}

val searchModule = module {
    viewModelOf(::SearchViewModel)
    singleOf(::FakeSearchAPI)
    singleOf(::GetSearchResults)
}