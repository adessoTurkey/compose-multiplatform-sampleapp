package com.example.moveeapp_compose_kmm.data.movie

import com.example.moveeapp_compose_kmm.domain.artist.Credits
import com.example.moveeapp_compose_kmm.domain.movie.MovieDetail
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import com.example.moveeapp_compose_kmm.domain.movie.NowPlayingMovie
import com.example.moveeapp_compose_kmm.domain.movie.PopularMovie
import com.example.moveeapp_compose_kmm.utils.resultOf

class MovieRepositoryImpl(
    private val service: MovieService
) : MovieRepository {
    override suspend fun getPopularMovie(): Result<List<PopularMovie>> {
        return resultOf {
            service.popularMovie().movies.map { it.toDomain() }
        }
    }

    override suspend fun getNowPlayingMovie(): Result<List<NowPlayingMovie>> {
        return resultOf {
            service.nowPlayingMovie().movies.map { it.toDomain() }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail> {
        return resultOf {
            service.movieDetail(movieId).toDomain()
        }
    }

    override suspend fun getMovieCredits(movieId: Int): Result<List<Credits>> {
        return resultOf {
            service.movieCredit(movieId).cast.map { it.toDomain() }
        }
    }
}
