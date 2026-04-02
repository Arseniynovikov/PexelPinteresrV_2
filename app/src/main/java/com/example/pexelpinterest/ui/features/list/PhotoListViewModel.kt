package com.example.pexelpinterest.ui.features.list


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _photo = MutableStateFlow<List<Photo>>(emptyList())
    val photo: StateFlow<List<Photo>> = _photo.asStateFlow()

    init{
        viewModelScope.launch {
            try {
                _photo.value = repository.getCuratedPhotos(1, 10)
            }catch (e: Exception){
                e.message?.let { Log.e("AAAAAAAAAAAAAAAAAAAAAAA", it) }
            }
        }
    }

}