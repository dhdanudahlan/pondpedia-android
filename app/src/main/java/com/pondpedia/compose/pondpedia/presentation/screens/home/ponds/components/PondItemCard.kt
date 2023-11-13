package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pondpedia.compose.pondpedia.R
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.presentation.Graph
import com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel.PondsEvent
import com.pondpedia.compose.pondpedia.presentation.ui.theme.Black

data class PondDropDownItem(
    val text: String
)
@Composable
fun PondItemSquareCard(
    pond: Pond,
    onEvent: (PondsEvent) -> Unit,
    onRouteChanged: (String) -> Unit
) {
    val dropDownItems = listOf(
        PondDropDownItem("Hapus Kolam")
    )

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(300.dp)
            .width(250.dp)
//            .clickable {
//                onEvent(PondsEvent.SelectPond(pond.pondId)); onRouteChanged(Graph.HOME_PONDS_POND)
//            }
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .indication(interactionSource = interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        },
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                            onEvent(PondsEvent.SelectPond(pond.pondId)); onRouteChanged(Graph.HOME_PONDS_POND)
                        }
                    )
                }
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
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight,
            )
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.text) },
                    onClick = {
                        onEvent(PondsEvent.DeletePond(pond.pondId))
                        isContextMenuVisible = false
                    }
                )
            }
        }
    }
}

@Composable
fun PondItemHexagonCard(
    pond: Pond,
    onEvent: (PondsEvent) -> Unit,
    onRouteChanged: (String) -> Unit
) {
    val myShape = HexagonShape()
    val edgeColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    Box(
        modifier = Modifier
            .padding(10.dp)
            .drawWithContent {
                drawContent()
                drawPath(
                    path = drawCustomHexagonPath(
                        size
                    ),
                    color = edgeColor,
                    alpha = 0.6f,
                    style = Stroke(
                        width = 20.dp.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(5f)
                    )
                )
            }
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_orange_yellow_grad),
            contentDescription = "My Hexagon Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentSize()
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = myShape
                    clip = true
                }
                .background(color = backgroundColor)
                .alpha(.09f)
                .size(180.dp)
                .clickable { onEvent(PondsEvent.SelectPond(pond.pondId)); onRouteChanged(Graph.HOME_PONDS_POND) }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = pond.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Terakhir diperbarui:",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(fontStyle = Italic),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = pond.updatedDate,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(fontStyle = Italic),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }

}
