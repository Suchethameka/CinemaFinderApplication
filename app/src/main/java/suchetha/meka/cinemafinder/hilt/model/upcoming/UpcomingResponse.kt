package suchetha.meka.cinemafinder.hilt.model.upcoming

import suchetha.meka.cinemafinder.hilt.model.local.Result
import suchetha.meka.cinemafinder.hilt.model.upcoming.Dates

data class UpcomingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)