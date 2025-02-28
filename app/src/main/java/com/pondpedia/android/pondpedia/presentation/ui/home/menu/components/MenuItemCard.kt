package com.pondpedia.android.pondpedia.presentation.ui.home.menu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.presentation.Graph
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.HexagonShape
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.drawCustomHexagonPath
import com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.android.pondpedia.presentation.theme.Black

@Composable
fun MenuItemSquareCard(
    pond: Pond,
    onEvent: (PondsEvent) -> Unit,
    onRouteChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp)
            .width(250.dp)
            .clickable {
                onEvent(PondsEvent.SelectPond(pond.pondId)); onRouteChanged(Graph.HOME_PONDS_POND)
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .border(1.dp, Black, RoundedCornerShape(4))
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Image(
                    painter = painterResource(R.drawable.pond_image_1),
                    contentDescription = "Pond Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(200.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = pond.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = pond.createdDate,
                    fontWeight = FontWeight.Normal,
//                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = pond.waterType,
                    fontWeight = FontWeight.Light,
//                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(fontStyle = Italic),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun MenuItemHexagonCard(
    icon: ImageVector,
    contentDescription: String = "",
    route: String,
    onRouteChanged: (String) -> Unit
) {
    val myShape = HexagonShape()
    val edgeColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    Box(
        modifier = Modifier
            .padding(5.dp)
            .drawWithContent {
                drawContent()
                drawPath(
                    path = drawCustomHexagonPath(
                        size
                    ),
                    color = edgeColor,
                    alpha = 0.6f,
                    style = Stroke(
                        width = 10.dp.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(3f)
                    )
                )
            }
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { onRouteChanged(route) }
            ) {
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
    }

}
