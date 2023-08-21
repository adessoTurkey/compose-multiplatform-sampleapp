package com.example.moveeapp_compose_kmm.ui.scene.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.moveeapp_compose_kmm.data.remote.model.PopularMovieModel
import com.example.moveeapp_compose_kmm.ui.components.MovieList
import com.example.moveeapp_compose_kmm.ui.scene.detailscreen.DetailScreen
import com.example.moveeapp_compose_kmm.utils.DataState
import com.example.moveeapp_compose_kmm.utils.Serializable
import org.koin.compose.LocalKoinScope

class HomeScreen : Screen, Serializable {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val koinScope = LocalKoinScope.current
        val viewModel: HomeViewModel = rememberScreenModel {
            koinScope.get()
        }
        HomeContent(viewModel = viewModel, navigator = navigator)
    }
}

@Composable
fun HomeContent(
    navigator: Navigator,
    viewModel: HomeViewModel
) {
    LaunchedEffect(viewModel) {
        viewModel.popularMovies(1)
    }

    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.popularMovieResponse.collectAsState().value?.let {
            when (it) {
                is DataState.Loading -> {
                    //ProgressIndicator()
                }

                is DataState.Success<List<PopularMovieModel.PopularMovies>> -> {
                    MovieList(it.data) {
                        navigator.push(DetailScreen())
                    }
                }

                is DataState.Error -> {
                    Text("${it.exception}")
                }
            }
        }
    }
}