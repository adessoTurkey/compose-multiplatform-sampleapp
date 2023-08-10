package com.example.moveeapp_compose_kmm.ui.scene.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.moveeapp_compose_kmm.data.remote.model.PopularMovieModel
import com.example.moveeapp_compose_kmm.navigation.Route
import com.example.moveeapp_compose_kmm.ui.components.MovieList
import com.example.moveeapp_compose_kmm.utils.DataState
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navigator: Navigator,
    onClick: () -> Unit,
    viewModel: HomeViewModel = koinInject()
) {
    LaunchedEffect(true) {
    viewModel.popularMovies(1)
}
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        viewModel.popularMovieResponse.collectAsState().value?.let {
            when (it) {
                is DataState.Loading -> {
                    //ProgressIndicator()
                }

                is DataState.Success<List<PopularMovieModel.PopularMovies>> -> {
                    MovieList(it.data) {
                        navigator.navigate(Route.Detail.route)
                    }
                }
                is DataState.Error ->{
                        Text("${it.exception}")
                }
            }
        }
    }
}
