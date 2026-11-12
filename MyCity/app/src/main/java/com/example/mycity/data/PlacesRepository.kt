package com.example.mycity.data

interface PlacesRepository {
    fun getPlacesByCategory(): Map<PlaceCategory, List<Place>>
}

class LocalPlacesRepository(
    private val places: List<Place> = LocalPlacesDataProvider.allPlaces
) : PlacesRepository {

    override fun getPlacesByCategory(): Map<PlaceCategory, List<Place>> {
        return PlaceCategory.entries.associateWith { category ->
            places.filter { it.category == category }
        }
    }
}
