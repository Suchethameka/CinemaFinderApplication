package suchetha.meka.cinemafinder.hilt.view.screens

import suchetha.meka.cinemafinder.R
import suchetha.meka.cinemafinder.hilt.model.Constants
import suchetha.meka.cinemafinder.hilt.viewmodel.FavouritesMovieViewModel
import suchetha.meka.cinemafinder.ui.theme.White
import suchetha.meka.cinemafinder.ui.theme.sp_20
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FavoritesScreenUI(navController: NavController,favouritesMovieViewModel: FavouritesMovieViewModel = hiltViewModel()) {
    val movieImageState = favouritesMovieViewModel.favMovies.observeAsState()
    val movieImage = movieImageState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.Favorites),
            fontWeight = FontWeight.Bold,
            fontSize = sp_20,
            modifier = Modifier
                .align(Alignment.Start)
        )

        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier
                .wrapContentWidth()
                .background(White)
        ) {
            if (movieImage != null) {
                items(movieImage.size) { item ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        val data = movieImage[item]
                        ItemView(
                            "${Constants.IMAGE_ENDPOINT + movieImage[item].posterPath}.toString()",
                            data.title,
                            data.releaseDate,
                            navController,
                            movieId = data.id.toString(),
                            data = data
                        )
                    }
                }
            } else {
                Log.e("error", movieImage.toString())
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPrev() {
    FavoritesScreenUI(rememberNavController())
}