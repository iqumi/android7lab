package com.example.mycity.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalPlacesRepositoryTest {

    @Test
    fun getPlacesByCategory_groupsPlacesByTheirCategory() {
        val placesByCategory = LocalPlacesRepository().getPlacesByCategory()

        placesByCategory.forEach { (category, places) ->
            assertTrue(places.all { it.category == category })
        }
    }

    @Test
    fun getPlacesByCategory_doesNotLosePlaces() {
        val placesByCategory = LocalPlacesRepository().getPlacesByCategory()

        assertEquals(
            LocalPlacesDataProvider.allPlaces.size,
            placesByCategory.values.sumOf { it.size }
        )
    }
}
