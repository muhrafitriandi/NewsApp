package com.yandey.news.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yandey.news.presentation.article.ArticlesScreen
import com.yandey.news.presentation.detail.ArticleDetailScreen

fun NavGraphBuilder.articleNavigation(
    navHostController: NavHostController,
) {
    composable<DestinationArticle> {
        ArticlesScreen(
            onOpenDetail = { url ->
                navHostController.navigate(DestinationDetailArticle(url))
            },
            onBack = navHostController::popBackStack
        )
    }

    composable<DestinationDetailArticle> {
        val url = it.arguments?.getString("url").orEmpty()
        ArticleDetailScreen(
            url = url,
            onBack = navHostController::popBackStack
        )
    }
}