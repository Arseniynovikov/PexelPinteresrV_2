package com.example.pexelpinterest.ui.features.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.domain.Photo
import com.example.pexelpinterest.navigation.DetailsScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandled: SavedStateHandle
) : ViewModel() {

    private val photoId: Long = savedStateHandled.toRoute<DetailsScreenRoute>().photoId

    private val _photo = MutableStateFlow<Photo?>(null)
    val photo = _photo

    init {
        viewModelScope.launch {
            try {
                _photo.value = repository.getPhotoById(photoId)
            } catch (e: Exception) {
                Log.e("DetailViewModelInit", e.message.toString())
            }
        }
    }

    fun addNewBookmarkPhoto(photo: Photo) {
        viewModelScope.launch {
            repository.insertBookmarkPhoto(photo)
        }
    }

}