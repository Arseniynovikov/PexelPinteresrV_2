package com.example.pexelpinterest.ui.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.example.pexelpinterest.domain.uistates.PhotoListUIState

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListScreen(
    viewModel: PhotoListViewModel = hiltViewModel(),
    onPhotoClick: (Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        PhotoList(
            state = uiState,
            onClick = onPhotoClick
        )
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = uiState.query,
                    onQueryChange = { viewModel.onQueryChange(it) },
                    onSearch = { viewModel.onActiveChange(false) },
                    expanded = false,
                    onExpandedChange = { viewModel.onActiveChange(it) },
                    placeholder = { Text("Search") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (uiState.query.isNotEmpty()) {
                            IconButton(onClick = { viewModel.onQueryChange("") }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    }
                )
            },
            expanded = false    ,
            onExpandedChange = { viewModel.onActiveChange(it) },
            content = {}
        )

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@Composable
fun PhotoList(state: PhotoListUIState, onClick: (Long) -> Unit) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.photos) { photo ->
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

    PhotoList(
        state = PhotoListUIState(
            photos = mockPhotos,
            query = "",
            isLoading = false,
            isSearchActive = false
        ),
        onClick = { })
}