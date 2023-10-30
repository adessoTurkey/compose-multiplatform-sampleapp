package com.example.moveeapp_compose_kmm.ui.tab

import com.example.moveeapp_compose_kmm.MR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

sealed class TabItem(
    val title: StringResource,
    val icon: ImageResource,
) {

    val key: String = this::class.simpleName!!

    class MoviesTab : TabItem(
        MR.strings.tab_movies,
        MR.images.ic_tabbar_movie,
    )

    class TvShowsTab : TabItem(
        MR.strings.tab_tv,
        MR.images.ic_tabbar_tv,
    )

    class SearchTab : TabItem(
        MR.strings.tab_search,
        MR.images.ic_tabbar_search,
    )

    class AccountTab : TabItem(
        MR.strings.tab_profile,
        MR.images.ic_tabbar_profile,
    )
}
