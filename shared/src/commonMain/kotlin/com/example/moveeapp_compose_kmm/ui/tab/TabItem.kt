package com.example.moveeapp_compose_kmm.ui.tab

import movee.shared.generated.resources.Res
import movee.shared.generated.resources.ic_tabbar_movie
import movee.shared.generated.resources.ic_tabbar_profile
import movee.shared.generated.resources.ic_tabbar_search
import movee.shared.generated.resources.ic_tabbar_tv_svg
import movee.shared.generated.resources.tab_movies
import movee.shared.generated.resources.tab_profile
import movee.shared.generated.resources.tab_search
import movee.shared.generated.resources.tab_tv
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class TabItem(
    val title: StringResource,
    val icon: DrawableResource,
) {

    val key: String = this::class.simpleName!!

    class MoviesTab : TabItem(
        Res.string.tab_movies,
        Res.drawable.ic_tabbar_movie,
    )

    class TvShowsTab : TabItem(
        Res.string.tab_tv,
        Res.drawable.ic_tabbar_tv_svg,
    )

    class SearchTab : TabItem(
        Res.string.tab_search,
        Res.drawable.ic_tabbar_search,
    )

    class AccountTab : TabItem(
        Res.string.tab_profile,
        Res.drawable.ic_tabbar_profile,
    )
}
