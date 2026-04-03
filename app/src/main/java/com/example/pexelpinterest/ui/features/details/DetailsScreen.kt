package com.example.pexelpinterest.ui.features.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pexelpinterest.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pexelpinterest.domain.uistates.PhotoLoadingResult
import com.example.pexelpinterest.domain.Photo

@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailsContent(
        state = uiState,
        onBackClick = onBackClick,
        onBookmarkButtonClick = { photo -> viewModel.toggleBookmark(photo) },
        onDownloadButtonClick = { photo -> }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    state: PhotoLoadingResult,
    onBackClick: () -> Unit,
    onBookmarkButtonClick: (Photo) -> Unit,
    onDownloadButtonClick: (Photo) -> Unit
) {
    when (state.photo) {
        null -> {}
        else -> {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = state.photo.author,
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                            }
                        }
                    )
                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { downloadPhoto(state.photo) },
                            modifier = Modifier.height(56.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5))
                        ) {
                            //Icon(painterResource(R.drawable.ic_download), contentDescription = null, tint = Color.Black)

                            Spacer(Modifier.width(8.dp))
                            Text("Download", color = Color.Black)
                        }


                        IconToggleButton(
                            checked = state.isBookmark,
                            onCheckedChange = { onBookmarkButtonClick(state.photo) },
                            modifier = Modifier
                                .size(56.dp),
                            shape = CircleShape
                        ) {
                            Icon(
                                painterResource(
                                    if (state.isBookmark) R.drawable.ic_bookmark_red
                                    else R.drawable.ic_bookmark
                                ), contentDescription = null
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp)
                ) {
                    AsyncImage(
                        model = state.photo.srcOriginal,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_placeholder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillWidth
                    )

                }
            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun DetailPreview() {

    val state = PhotoLoadingResult(
        Photo(
            0.toLong(),
            "oleg oleg oleg",
            "",
            "",
            "",
            ""
        ),
        isBookmark = true,
        isLoading = false
    )
    DetailsContent(
        state,
        {},
        {},
        {}
    )
}

private fun downloadPhoto(photo: Photo) {

}
