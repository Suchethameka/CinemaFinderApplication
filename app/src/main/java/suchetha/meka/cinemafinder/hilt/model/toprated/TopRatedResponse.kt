package suchetha.meka.cinemafinder.hilt.model.toprated

import suchetha.meka.cinemafinder.hilt.model.local.Result

data class TopRatedResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)