package com.example.pexelpinterest.ui.features.bookmarks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pexelpinterest.R
import com.example.pexelpinterest.domain.Photo
import com.example.pexelpinterest.ui.features.list.PhotoListViewModel

@Composable
fun BookmarkItem(photo: Photo, onClick: () -> Unit) {
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
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
    onPhotoClick: (Long) -> Unit
) {

    val photos by viewModel.photoState.collectAsState()

    BookmarkList(photos, onClick = onPhotoClick)
}

@Composable
fun BookmarkList(photos: List<Photo>, onClick: (Long) -> Unit) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos) { photo ->
            BookmarkItem(photo) { onClick(photo.id) }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun BookmarkListScreenPreview() {

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

    BookmarkList(photos = mockPhotos){}
}