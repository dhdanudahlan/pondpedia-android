package com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseUser
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.components.MediumSpacer
import com.pondpedia.android.pondpedia.components.SmallSpacer
import com.pondpedia.android.pondpedia.core.util.Constants.DISPLAY_NAME
import com.pondpedia.android.pondpedia.core.util.Constants.EMAIL
import com.pondpedia.android.pondpedia.core.util.Constants.PHOTO_URL
import com.pondpedia.android.pondpedia.core.util.Constants.WELCOME_MESSAGE
import com.pondpedia.android.pondpedia.domain.model.auth.Farmer

@Composable
fun ProfileContent(
    padding: PaddingValues,
    user: Farmer,
    sendEmailVerification: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.TopCenter
    ) {
        var name by rememberSaveable{
            mutableStateOf<String?>(null)
        }
        var email by rememberSaveable{
            mutableStateOf<String?>(null)
        }
        var photoUrl by rememberSaveable{
            mutableStateOf<String?>(null)
        }
        var emailVerified by rememberSaveable{
            mutableStateOf<Boolean>(false)
        }
        val scrollState = rememberScrollState()

        user.let {
            name = it.name
            email = it.email
            photoUrl = it.profilePicture
            emailVerified = it.verified
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(-75.dp),
        ) {
            // Box A (Top) - Dark Blue
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .zIndex(1f)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (photoUrl != null ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(photoUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp, Color.DarkGray), CircleShape)
                            .width(96.dp)
                            .height(96.dp)
                            .background(color = Color.White),
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp, Color.DarkGray), CircleShape)
                            .size(100.dp)
                            .background(color = Color.White),
                        painter = painterResource(id = R.drawable.pondpedia_logo), // Replace with your image resource
                        contentDescription = "Circle image"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier.height(100.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    /*Card(
//                        colors = CardDefaults.cardColors(
//                            containerColor = MaterialTheme.colorScheme.primaryContainer
//                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (emailVerified) Icons.Default.Verified else Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = colorResource(id = R.color.navi)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = if (emailVerified) stringResource(id = R.string.verified) else stringResource(id = R.string.unverified),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }*/
                }
            }
            // Box B (Bottom) - Dark Gray
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .zIndex(0f)
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 70.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileMainCard(
                        displayName = name,
                        email = email,
                        emailVerified = emailVerified,
                        sendEmailVerification = sendEmailVerification
                    )
                }
            }
        }
    }
}
@Composable
fun ProfileMainCard(
    displayName: String? = null,
    email: String? = null,
    emailVerified: Boolean = false,
    sendEmailVerification: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),

        ) {
            Text(
                text = displayName ?: "Placeholder.Name",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            SmallSpacer()
            Text(
                text = email ?: "Placeholder.Email",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            MediumSpacer()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier,
                    onClick = { sendEmailVerification() },
                    enabled = !emailVerified
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = stringResource(id = R.string.verify_profile_label))
                    }
                }
                Button(
                    modifier = Modifier,
                    onClick = { /*TODO*/ }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = stringResource(id = R.string.edit_profile_label))
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    ProfileContent(
        padding = PaddingValues(16.dp),
        user = Farmer(),
        sendEmailVerification = {}
    )
}