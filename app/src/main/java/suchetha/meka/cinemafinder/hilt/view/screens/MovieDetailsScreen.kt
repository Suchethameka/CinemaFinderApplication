package suchetha.meka.cinemafinder.hilt.view.screens

import suchetha.meka.cinemafinder.R
import suchetha.meka.cinemafinder.hilt.model.Constants.IMAGE_ENDPOINT
import suchetha.meka.cinemafinder.hilt.viewmodel.DetailsViewModel
import suchetha.meka.cinemafinder.ui.theme.dp_10
import suchetha.meka.cinemafinder.ui.theme.dp_100
import suchetha.meka.cinemafinder.ui.theme.dp_120
import suchetha.meka.cinemafinder.ui.theme.dp_20
import suchetha.meka.cinemafinder.ui.theme.dp_30
import suchetha.meka.cinemafinder.ui.theme.dp_5
import suchetha.meka.cinemafinder.ui.theme.dp_50
import suchetha.meka.cinemafinder.ui.theme.sp_12
import suchetha.meka.cinemafinder.ui.theme.sp_14
import suchetha.meka.cinemafinder.ui.theme.sp_15
import suchetha.meka.cinemafinder.ui.theme.sp_20
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun MovieDetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    movieId: String
) {
    detailsViewModel.fetchMovieDetails(movieId)
    val movieImage = detailsViewModel.detailsLiveData.observeAsState()
    val errorData = detailsViewModel.detailsErrorData.observeAsState()
    val basicDetails = movieImage.value
    basicDetails?.let {
        val genres = basicDetails.genres
        val stringBuilder = StringBuilder()
        for (i in genres.indices) {
            stringBuilder.append(genres[i].name)

            // Add a comma if it's not the last genre
            if (i < genres.size - 1) {
                stringBuilder.append(",")
            }
            val concatenatedGenres = stringBuilder.toString()

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Row(
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    ImageButton(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = stringResource(id = R.string.Button_description),
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(top = dp_10, bottom = dp_10)
                    )
                    Text(
                        text = basicDetails.title,
                        fontSize = sp_20,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(dp_10)
                            .align(Alignment.CenterVertically)
                    )
                }

                Box {
                    basicDetails?.belongs_to_collection?.backdrop_path?.let {
                        val url1 =
                            "${IMAGE_ENDPOINT + it}"

                        GlideImage(
                            model = url1,
                            contentDescription = null,
                            loading = placeholder(R.drawable.ic_launcher_background),
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(dp_20)
                        )
                    }

                    basicDetails?.belongs_to_collection?.poster_path?.let {
                        val url2 =
                            "${IMAGE_ENDPOINT + basicDetails.belongs_to_collection.poster_path}"

                        GlideImage(
                            model = url2,
                            contentDescription = null,
                            loading = placeholder(R.drawable.ic_launcher_background),
                            modifier = Modifier
                                .size(dp_120)
                                .padding(start = dp_10, top = dp_20, bottom = dp_10)
                                .align(Alignment.CenterStart)
                        )
                    }
                }
                Text(
                    text = basicDetails.original_language,
                    fontSize = sp_20,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = dp_10, start = dp_10, end = dp_10
                    )
                )
                Text(
                    text = "R ${basicDetails.release_date}(${basicDetails.original_language}) ${basicDetails.runtime}",   //R 2022-12-14(EN) 3h 12m
                    fontSize = sp_15
                )

                Text(
                    text = concatenatedGenres,
                    fontSize = sp_12,
                    color = Color.Gray
                )
                Row(modifier = Modifier.padding(dp_30)) {
                    Text(
                        text = stringResource(id = R.string.user_Score),
                        fontSize = sp_14,
                        color = Color.Black
                    )
                    Text(
                        text = "|",
                        fontSize = sp_14,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(id = R.string.Play_trailer),
                        fontSize = sp_14,
                        color = Color.Black
                    )
                }
                Text(
                    text = stringResource(id = R.string.Overview),
                    fontSize = sp_20,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = dp_10)
                )
                Text(
                    text = basicDetails.overview,
                    maxLines = 1,
                    fontSize = sp_14,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(dp_10)
                )
                Text(
                    text = stringResource(id = R.string.Cast_bill),
                    fontSize = sp_20,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(dp_10)
                )
                CastList()
            }
        }



    } ?: run {
        Text(text = (errorData.value.toString()))
    }
}

data class TopBilledCastList(val img: Int, val name: String)

val listCast = listOf(
    TopBilledCastList(R.drawable.ic_launcher_background, "Sam Worthington"),
    TopBilledCastList(R.drawable.ic_launcher_background, "Zoe Saldana"),
    TopBilledCastList(R.drawable.ic_launcher_background, "Dallas Liu"),
    TopBilledCastList(R.drawable.ic_launcher_background, "Stephen Lang")
)

@Composable
fun CastList() {
    LazyRow {
        items(listCast) {
            ItemTopBilledCast(it)
        }
    }
}

@Composable
fun ImageButton(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(dp_50)
                .wrapContentSize() // To match the size of the image
        )
    }
}

@Composable
fun ItemTopBilledCast(topBilledCastList: TopBilledCastList) {
    Row {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = topBilledCastList.img), contentDescription = null,
                modifier = Modifier
                    .size(dp_100)
                    .padding(dp_10)
            )
            Text(
                text = topBilledCastList.name,
                fontSize = sp_14,
                modifier = Modifier.padding(dp_5)
            )
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPrev() {
    MovieDetailsScreen(navController = rememberNavController(), hiltViewModel(), "123")
}