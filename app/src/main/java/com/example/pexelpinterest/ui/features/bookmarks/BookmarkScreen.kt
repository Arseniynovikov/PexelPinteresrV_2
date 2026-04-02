package com.example.pexelpinterest.ui.features.bookmarks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookmarkScreen() {
    Scaffold {innerPadding ->
        Text("Bookmark", Modifier.padding(innerPadding))
    }
}

@Composable
@Preview(showSystemUi = true)
fun BookmarkScreenPreview(){
    BookmarkScreen()
}