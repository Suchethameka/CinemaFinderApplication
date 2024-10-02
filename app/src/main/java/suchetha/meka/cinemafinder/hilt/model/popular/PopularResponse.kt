package suchetha.meka.cinemafinder.hilt.model.popular

import suchetha.meka.cinemafinder.hilt.model.local.Result

data class PopularResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)