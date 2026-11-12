package com.example.mycity.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mycity.R

enum class PlaceCategory(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    Restaurants(title = R.string.category_restaurants, icon = Icons.Filled.Restaurant),
    Parks(title = R.string.category_parks, icon = Icons.Filled.Park),
    Coffee(title = R.string.category_coffee, icon = Icons.Filled.LocalCafe),
    Cinemas(title = R.string.category_cinemas, icon = Icons.Filled.Movie),
    Malls(title = R.string.category_malls, icon = Icons.Filled.ShoppingBag)
}
