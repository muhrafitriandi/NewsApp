package com.yandey.news.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object DestinationArticle

@Serializable
data class DestinationDetailArticle(val url: String)