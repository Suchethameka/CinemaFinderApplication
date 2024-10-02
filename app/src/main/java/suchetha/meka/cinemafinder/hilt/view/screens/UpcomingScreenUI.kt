package suchetha.meka.cinemafinder.hilt.view.screens

import suchetha.meka.cinemafinder.R
import suchetha.meka.cinemafinder.hilt.model.Constants.IMAGE_ENDPOINT
import suchetha.meka.cinemafinder.hilt.viewmodel.MovieListsViewModel
import suchetha.meka.cinemafinder.navigation.NavigationItem
import suchetha.meka.cinemafinder.ui.theme.White
import suchetha.meka.cinemafinder.ui.theme.sp_20
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UpcomingScreenUI(upcomingViewModel: MovieListsViewModel = hiltViewModel(), navController: NavController) {
    upcomingViewModel.fetchUpcomingMovieDetails()
    val movieImage = upcomingViewModel.upcomingLiveData.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = White)
        ) {
            IconButton(
                onClick = {  navController.navigate(NavigationItem.WELCOME.route)},
                Modifier.paint(painterResource(id = R.drawable.baseline_arrow_back_24))
            ) { }

            Text(
                text = stringResource(id = R.string.upcoming),
                fontWeight = FontWeight.Bold,
                fontSize = sp_20,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        val columns = 3
        LazyVerticalGrid(
            GridCells.Fixed(columns),
            modifier = Modifier
                .wrapContentWidth()
                .background(White)
        ) {
            val list = movieImage.value?.results
            if (list != null) {
                items(list.size) { item ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        val data = list[item]
                        ItemView(
                            "${IMAGE_ENDPOINT + list[item].posterPath}.toString()",
                            data.title,
                            data.releaseDate,
                            navController,
                            movieId = data.id.toString(),
                            data = data
                        )
                    }
                }
            } else {
                Log.e("error", list.toString())
            }
        }
    }
}
