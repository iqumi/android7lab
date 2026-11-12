package com.example.mycity.ui

import com.example.mycity.data.Place
import com.example.mycity.data.PlaceCategory

data class MyCityUiState(
    val placesByCategory: Map<PlaceCategory, List<Place>> = emptyMap(),
    val selectedCategory: PlaceCategory = PlaceCategory.entries.first(),
    val selectedPlace: Place? = null
) {
    val currentPlaces: List<Place>
        get() = placesByCategory[selectedCategory].orEmpty()

    val currentPlace: Place?
        get() = selectedPlace?.takeIf { it.category == selectedCategory } ?: currentPlaces.firstOrNull()
}
