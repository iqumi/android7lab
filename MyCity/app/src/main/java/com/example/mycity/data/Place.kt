package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    val category: PlaceCategory,
    @StringRes val name: Int,
    @StringRes val description: Int?,
    @StringRes val address: Int,
    @StringRes val phone: Int?,
    @StringRes val website: Int?,
    val rating: Float,
    @DrawableRes val image: Int
)
