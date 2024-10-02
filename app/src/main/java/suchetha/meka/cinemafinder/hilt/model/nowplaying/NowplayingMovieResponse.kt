package suchetha.meka.cinemafinder.hilt.model.nowplaying

import suchetha.meka.cinemafinder.hilt.model.local.Result
import suchetha.meka.cinemafinder.hilt.model.nowplaying.Dates

data class NowplayingMovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)