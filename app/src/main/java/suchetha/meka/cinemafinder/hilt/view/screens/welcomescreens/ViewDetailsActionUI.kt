package suchetha.meka.cinemafinder.hilt.view.screens.welcomescreens

import suchetha.meka.cinemafinder.R
import suchetha.meka.cinemafinder.ui.theme.White
import suchetha.meka.cinemafinder.ui.theme.dp_32
import suchetha.meka.cinemafinder.ui.theme.sp_16
import suchetha.meka.cinemafinder.ui.theme.sp_20
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun ViewDetailsActionUI(navController: NavController, title: String, route: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = White),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = sp_20,
            modifier = Modifier
                .align(Alignment.Top)
        )

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    navController.navigate(route)
                }) {
                Text(
                    text = stringResource(id = R.string.view_details),
                    fontSize = sp_16,
                    modifier = Modifier,
                    color = Color.Gray
                )

                IconButton(
                    onClick = { navController.navigate(route) },
                    modifier = Modifier.size(dp_32)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_double_arrow_right_24_popular),
                        contentDescription = null
                    )
                }
            }
        }
    }
}