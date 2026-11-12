package com.example.mycity.ui.utils

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

enum class MyCityNavigationType {
    STACK, PERMANENT_NAVIGATION_DRAWER
}

enum class MyCityContentType {
    LIST_ONLY, LIST_AND_DETAIL
}

fun WindowWidthSizeClass.toNavigationType(): MyCityNavigationType = when (this) {
    WindowWidthSizeClass.Expanded -> MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
    else -> MyCityNavigationType.STACK
}

fun WindowWidthSizeClass.toContentType(): MyCityContentType = when (this) {
    WindowWidthSizeClass.Expanded -> MyCityContentType.LIST_AND_DETAIL
    else -> MyCityContentType.LIST_ONLY
}
