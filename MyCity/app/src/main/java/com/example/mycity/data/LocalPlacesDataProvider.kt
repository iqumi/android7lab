package com.example.mycity.data

import com.example.mycity.R

object LocalPlacesDataProvider {

    val allPlaces: List<Place> = listOf(
        Place(
            id = 1,
            category = PlaceCategory.Restaurants,
            name = R.string.place_twiany_restaurant_name,
            description = R.string.place_twiany_restaurant_description,
            address = R.string.place_twiany_restaurant_address,
            phone = R.string.place_twiany_restaurant_phone,
            website = R.string.place_twiany_restaurant_website,
            rating = 5.0f,
            image = R.drawable.twiany_restaurant
        ),
        Place(
            id = 2,
            category = PlaceCategory.Restaurants,
            name = R.string.place_hachapuri_mariko_name,
            description = R.string.place_hachapuri_mariko_description,
            address = R.string.place_hachapuri_mariko_address,
            phone = R.string.place_hachapuri_mariko_phone,
            website = R.string.place_hachapuri_mariko_website,
            rating = 4.9f,
            image = R.drawable.hachapuri_mariko
        ),
        Place(
            id = 3,
            category = PlaceCategory.Restaurants,
            name = R.string.place_sadj_name,
            description = R.string.place_sadj_description,
            address = R.string.place_sadj_address,
            phone = R.string.place_sadj_phone,
            website = R.string.place_sadj_website,
            rating = 4.7f,
            image = R.drawable.sadj
        ),
        Place(
            id = 4,
            category = PlaceCategory.Parks,
            name = R.string.place_park_angelov_name,
            description = R.string.place_park_angelov_description,
            address = R.string.place_park_angelov_address,
            phone = null,
            website = R.string.place_park_angelov_website,
            rating = 4.9f,
            image = R.drawable.park_angelov
        ),
        Place(
            id = 5,
            category = PlaceCategory.Parks,
            name = R.string.place_park_zhukova_name,
            description = R.string.place_park_zhukova_description,
            address = R.string.place_park_zhukova_address,
            phone = null,
            website = null,
            rating = 4.8f,
            image = R.drawable.park_zhukova
        ),
        Place(
            id = 6,
            category = PlaceCategory.Parks,
            name = R.string.place_anton_name,
            description = R.string.place_anton_description,
            address = R.string.place_anton_address,
            phone = null,
            website = null,
            rating = 4.4f,
            image = R.drawable.anton
        ),
        Place(
            id = 7,
            category = PlaceCategory.Coffee,
            name = R.string.place_port_coffee_name,
            description = R.string.place_port_coffee_description,
            address = R.string.place_port_coffee_address,
            phone = R.string.place_port_coffee_phone,
            website = R.string.place_port_coffee_website,
            rating = 4.9f,
            image = R.drawable.port_coffee
        ),
        Place(
            id = 8,
            category = PlaceCategory.Coffee,
            name = R.string.place_like_name,
            description = R.string.place_like_description,
            address = R.string.place_like_address,
            phone = R.string.place_like_phone,
            website = R.string.place_like_website,
            rating = 4.7f,
            image = R.drawable.like
        ),
        Place(
            id = 9,
            category = PlaceCategory.Coffee,
            name = R.string.place_surf_name,
            description = R.string.place_surf_description,
            address = R.string.place_surf_address,
            phone = R.string.place_surf_phone,
            website = R.string.place_surf_website,
            rating = 4.8f,
            image = R.drawable.surf
        ),
        Place(
            id = 10,
            category = PlaceCategory.Cinemas,
            name = R.string.place_ub_name,
            description = null,
            address = R.string.place_ub_address,
            phone = R.string.place_ub_phone,
            website = R.string.place_ub_website,
            rating = 4.8f,
            image = R.drawable.ub
        ),
        Place(
            id = 11,
            category = PlaceCategory.Cinemas,
            name = R.string.place_starmax_name,
            description = null,
            address = R.string.place_starmax_address,
            phone = R.string.place_starmax_phone,
            website = R.string.place_starmax_website,
            rating = 4.7f,
            image = R.drawable.starmax
        ),
        Place(
            id = 12,
            category = PlaceCategory.Cinemas,
            name = R.string.place_kuzbasskino_name,
            description = null,
            address = R.string.place_kuzbasskino_address,
            phone = R.string.place_kuzbasskino_phone,
            website = R.string.place_kuzbasskino_website,
            rating = 4.9f,
            image = R.drawable.kuzbasskino
        ),
        Place(
            id = 13,
            category = PlaceCategory.Malls,
            name = R.string.place_letocity_name,
            description = null,
            address = R.string.place_letocity_address,
            phone = R.string.place_letocity_phone,
            website = R.string.place_letocity_website,
            rating = 4.9f,
            image = R.drawable.letocity
        ),
        Place(
            id = 14,
            category = PlaceCategory.Malls,
            name = R.string.place_gr_name,
            description = null,
            address = R.string.place_gr_address,
            phone = null,
            website = null,
            rating = 4.6f,
            image = R.drawable.gr
        ),
        Place(
            id = 15,
            category = PlaceCategory.Malls,
            name = R.string.place_oblaka_name,
            description = null,
            address = R.string.place_oblaka_address,
            phone = R.string.place_oblaka_phone,
            website = R.string.place_oblaka_website,
            rating = 4.9f,
            image = R.drawable.oblaka
        )
    )
}
