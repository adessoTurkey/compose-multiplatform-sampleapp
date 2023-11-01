package com.example.moveeapp_compose_kmm.data.movie

import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val service: MovieService
) : MovieRepository {
    override fun getPopularMovie() = flow {
        emit(
            resultOf {
                service.popularMovie().movies.map { it.toDomain() }
            }
        )
    }


    override fun getNowPlayingMovie() = flow {
        emit(
            resultOf {
                service.nowPlayingMovie().movies.map { it.toDomain() }
            }
        )
    }

    override fun getMovieDetail(movieId: Int) = flow {
        emit(
            resultOf {
                service.movieDetail(movieId).toDomain()
            }
        )
    }

    override fun getMovieCredits(movieId: Int) = flow {
        emit(
            resultOf {
                service.movieCredit(movieId).cast.map { it.toDomain() }
            }
        )
    }
}
