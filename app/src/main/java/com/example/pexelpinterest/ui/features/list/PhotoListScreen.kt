package com.example.pexelpinterest.ui.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pexelpinterest.R
import com.example.pexelpinterest.domain.Photo
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale


@Composable
fun PhotoItem(photo: Photo, onClick: () -> Unit) {
    AsyncImage(
        model = photo.srcMedium,
        contentDescription = null,
        placeholder = painterResource(R.drawable.ic_placeholder),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun PhotoListScreen(
    viewModel: PhotoListViewModel = hiltViewModel(),
    onPhotoClick: (Long) -> Unit
) {
    PhotoList(viewModel.photo.collectAsState().value, onClick = onPhotoClick)
}

@Composable
fun PhotoList(photos: List<Photo>, onClick: (Long) -> Unit) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos) { photo ->
            PhotoItem(photo) { onClick(photo.id) }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PhotoListScreenPreview() {

    val mockPhotos = List(6) { index ->
        Photo(
            id = index.toLong(),
            author = "Author $index",
            srcMedium = "",
            srcOriginal = "",
            srcSmall = "",
            url = ""
        )
    }

    PhotoList(photos = mockPhotos){}
}