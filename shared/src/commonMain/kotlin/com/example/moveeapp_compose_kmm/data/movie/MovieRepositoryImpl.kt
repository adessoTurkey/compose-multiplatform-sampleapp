package com.example.moveeapp_compose_kmm.data.movie

import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.favorite.MovieAccountState
import com.example.moveeapp_compose_kmm.domain.artist.Credits
import com.example.moveeapp_compose_kmm.domain.movie.MovieDetail
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import com.example.moveeapp_compose_kmm.domain.movie.NowPlayingMovie
import com.example.moveeapp_compose_kmm.domain.movie.PopularMovie
import com.example.moveeapp_compose_kmm.utils.resultOf

class MovieRepositoryImpl(
    private val service: MovieService,
    private val sessionSettings: SessionSettings
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

    override suspend fun getMovieAccountState(movieId: Int): Result<MovieAccountState> {
        return resultOf {
            val response = service.getMovieState(
                sessionId = sessionSettings.getSessionId() ?: "",
                movieId
            )
            MovieAccountState(response.favorite ?: false, response.rated?.value)
        }
    }
}
