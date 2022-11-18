package org.sopt.sample.home.data

import androidx.annotation.DrawableRes

data class RepoData(
    val repos: List<RepoInfo>,
    val title: Title
) {
    data class RepoInfo(
        @DrawableRes val image: Int,
        val name: String,
        val author: String
    )

    data class Title(
        val title: String = "tamazzang"
    )
}
