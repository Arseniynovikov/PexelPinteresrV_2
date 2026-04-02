package com.example.pexelpinterest.ui.features.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.pexelpinterest.domain.Photo

@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val photoState by viewModel.photo.collectAsState()

    // Передаем данные в чистый UI
    photoState?.let { photo ->
        DetailsScreenPreview(
            photo = photo,
            onBookmarkButtonClick = {
                viewModel.addNewBookmarkPhoto(photo)
            })
    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenPreview(
    photo: Photo,
    onBookmarkButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = photo.author, style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
        ) {
            AsyncImage(
                model = photo.srcOriginal,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { downloadPhoto(photo) },
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    //Icon(painterResource(R.drawable.ic_download), contentDescription = null, tint = Color.Black)

                    Spacer(Modifier.width(8.dp))
                    Text("Download", color = Color.Black)
                }


                OutlinedIconButton(
                    onClick = onBookmarkButtonClick ,
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape
                ) {
                    Icon(painterResource(R.drawable.ic_bookmark), contentDescription = null)
                }
            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun DetailPreview() {

    DetailsScreenPreview(
        Photo(
            0,
            "oleg oleg oleg",
            "",
            "",
            "",
            ""
        ),
        {}
    )
}

private fun downloadPhoto(photo: Photo){

}
