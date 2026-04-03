package com.example.pexelpinterest.ui.features.details

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import com.example.pexelpinterest.domain.uistates.PhotoLoadingResult
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.domain.Photo
import com.example.pexelpinterest.navigation.DetailsScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandled: SavedStateHandle
) : ViewModel() {

    private val photoId: Long = savedStateHandled.toRoute<DetailsScreenRoute>().photoId

    val uiState = repository.getPhotoResultById(photoId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PhotoLoadingResult(isLoading = true)
    )

    fun toggleBookmark(photo: Photo) {
        viewModelScope.launch {
            if (uiState.value.isBookmark) {
                repository.deleteBookmarkPhoto(photo)
            }
            else{
                repository.insertBookmarkPhoto(photo)
            }
        }
    }

}