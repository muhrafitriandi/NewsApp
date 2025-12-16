package com.yandey.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yandey.news.presentation.navigation.DestinationArticle
import com.yandey.news.presentation.navigation.articleNavigation

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = DestinationArticle
    ) {
        articleNavigation(navHostController)
    }
}