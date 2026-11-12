package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.LocalPlacesRepository
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceCategory
import com.example.mycity.data.PlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel(
    repository: PlacesRepository = LocalPlacesRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MyCityUiState(placesByCategory = repository.getPlacesByCategory())
    )
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun selectCategory(category: PlaceCategory) {
        _uiState.update { currentState ->
            currentState.copy(selectedCategory = category, selectedPlace = null)
        }
    }

    fun selectPlace(place: Place) {
        _uiState.update { currentState ->
            currentState.copy(selectedCategory = place.category, selectedPlace = place)
        }
    }
}
