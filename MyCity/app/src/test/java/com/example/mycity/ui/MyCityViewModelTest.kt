package com.example.mycity.ui

import com.example.mycity.data.LocalPlacesDataProvider
import com.example.mycity.data.LocalPlacesRepository
import com.example.mycity.data.PlaceCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MyCityViewModelTest {

    private val viewModel = MyCityViewModel(LocalPlacesRepository())

    @Test
    fun uiState_initially_containsPlacesForEveryCategory() {
        val placesByCategory = viewModel.uiState.value.placesByCategory

        assertEquals(PlaceCategory.entries.toSet(), placesByCategory.keys)
        placesByCategory.values.forEach { places ->
            assertTrue(places.size >= MIN_PLACES_PER_CATEGORY)
        }
    }

    @Test
    fun uiState_initially_hasNoSelectedPlace() {
        assertNull(viewModel.uiState.value.selectedPlace)
    }

    @Test
    fun selectCategory_updatesCurrentPlaces() {
        viewModel.selectCategory(PlaceCategory.Parks)

        val state = viewModel.uiState.value

        assertEquals(PlaceCategory.Parks, state.selectedCategory)
        assertTrue(state.currentPlaces.all { it.category == PlaceCategory.Parks })
    }

    @Test
    fun selectPlace_updatesSelectedPlaceAndCategory() {
        val place = LocalPlacesDataProvider.allPlaces.last()

        viewModel.selectPlace(place)

        val state = viewModel.uiState.value
        assertEquals(place, state.selectedPlace)
        assertEquals(place.category, state.selectedCategory)
    }

    @Test
    fun selectCategory_resetsSelectedPlace_andCurrentPlaceFallsBackToFirstOfCategory() {
        viewModel.selectPlace(LocalPlacesDataProvider.allPlaces.first())

        viewModel.selectCategory(PlaceCategory.Cinemas)

        val state = viewModel.uiState.value
        assertNull(state.selectedPlace)
        assertEquals(state.currentPlaces.first(), state.currentPlace)
    }

    private companion object {
        const val MIN_PLACES_PER_CATEGORY = 3
    }
}
