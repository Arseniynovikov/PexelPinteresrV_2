package com.example.pexelpinterest.ui.features.list


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.domain.Photo
import com.example.pexelpinterest.domain.uistates.PhotoListUIState
import com.example.pexelpinterest.domain.uistates.PhotoLoadingResult
import com.example.pexelpinterest.domain.uistates.PhotosLoadingResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _isSearchActive = MutableStateFlow(false)


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val _photosData = _searchQuery
        .debounce { query -> if (query.isBlank()) 0L else 500L }
        .flatMapLatest { query ->
            flow {
                emit(PhotosLoadingResult(isLoading = true))
                val result = if (query.isBlank()) {
                    repository.getCuratedPhotos(1, 10)
                } else {
                    repository.searchPhotos(query, 1, 10)
                }
                emitAll(result.map { list -> PhotosLoadingResult(photos = list, isLoading = false) })
            }
        }

    val uiState: StateFlow<PhotoListUIState> = combine(
        _photosData,
        _searchQuery,
        _isSearchActive
    ) { data, query, active ->
        PhotoListUIState(
            photos = data.photos,
            query = query,
            isLoading = data.isLoading,
            isSearchActive = active
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PhotoListUIState(isLoading = true)
        )

    fun onQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onActiveChange(isActive: Boolean) {
        _isSearchActive.value = isActive
    }

}