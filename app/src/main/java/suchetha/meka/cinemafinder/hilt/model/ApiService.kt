package suchetha.meka.cinemafinder.hilt.model

import suchetha.meka.cinemafinder.hilt.model.Constants.DETAILS_ENDPOINT
import suchetha.meka.cinemafinder.hilt.model.Constants.NOWPLAYING_MOVIE_ENDPOINT
import suchetha.meka.cinemafinder.hilt.model.Constants.POPULAR_MOVIE_ENDPOINT
import suchetha.meka.cinemafinder.hilt.model.Constants.TOPRATED_MOVIE_ENDPOINT
import suchetha.meka.cinemafinder.hilt.model.Constants.UPCOMING_MOVIE_ENDPOINT
import suchetha.meka.cinemafinder.hilt.model.details.MovieDetailsResponse
import suchetha.meka.cinemafinder.hilt.model.toprated.TopRatedResponse
import suchetha.meka.cinemafinder.hilt.model.nowplaying.NowplayingMovieResponse
import suchetha.meka.cinemafinder.hilt.model.popular.PopularResponse
import suchetha.meka.cinemafinder.hilt.model.upcoming.UpcomingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(NOWPLAYING_MOVIE_ENDPOINT)
    fun getNowPlayingMovie(
        @Query("api_key") apiKey: String
    ): Single<NowplayingMovieResponse>
    @GET(POPULAR_MOVIE_ENDPOINT)
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Single<PopularResponse>
    @GET(UPCOMING_MOVIE_ENDPOINT)
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String
    ): Single<UpcomingResponse>
    @GET(TOPRATED_MOVIE_ENDPOINT)
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Single<TopRatedResponse>
    @GET(DETAILS_ENDPOINT)
    fun getDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailsResponse>
}
