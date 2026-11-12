package com.example.mycity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.data.PlaceCategory
import com.example.mycity.ui.utils.MyCityContentType
import com.example.mycity.ui.utils.MyCityNavigationType
import com.example.mycity.ui.utils.toContentType
import com.example.mycity.ui.utils.toNavigationType

enum class MyCityScreen {
    Categories, Places, Detail
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )
    val navigationType = windowSize.toNavigationType()
    val contentType = windowSize.toContentType()

    val onCategoryClick = { category: PlaceCategory ->
        viewModel.selectCategory(category)
        navController.navigate(MyCityScreen.Places.name) {
            launchSingleTop = true
        }
    }

    if (navigationType == MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    NavigationDrawerContent(
                        selectedCategory = uiState.selectedCategory,
                        showSelection = currentScreen != MyCityScreen.Categories,
                        onCategoryClick = onCategoryClick,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(dimensionResource(R.dimen.drawer_padding_content))
                    )
                }
            }
        ) {
            MyCityContent(
                contentType = contentType,
                uiState = uiState,
                viewModel = viewModel,
                navController = navController,
                currentScreen = currentScreen,
                onCategoryClick = onCategoryClick
            )
        }
    } else {
        MyCityContent(
            contentType = contentType,
            uiState = uiState,
            viewModel = viewModel,
            navController = navController,
            currentScreen = currentScreen,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
private fun MyCityContent(
    contentType: MyCityContentType,
    uiState: MyCityUiState,
    viewModel: MyCityViewModel,
    navController: NavHostController,
    currentScreen: MyCityScreen,
    onCategoryClick: (PlaceCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val title = when (currentScreen) {
        MyCityScreen.Categories -> stringResource(R.string.app_name)
        MyCityScreen.Places -> stringResource(uiState.selectedCategory.title)
        MyCityScreen.Detail -> uiState.selectedPlace?.let { stringResource(it.name) }
            ?: stringResource(uiState.selectedCategory.title)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MyCityAppBar(
                title = title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Categories.name) {
                CategoryListScreen(
                    categories = PlaceCategory.entries,
                    onCategoryClick = onCategoryClick
                )
            }
            composable(route = MyCityScreen.Places.name) {
                if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                    PlaceListAndDetailScreen(
                        places = uiState.currentPlaces,
                        selectedPlace = uiState.currentPlace,
                        onPlaceClick = { viewModel.selectPlace(it) }
                    )
                } else {
                    PlaceListScreen(
                        places = uiState.currentPlaces,
                        onPlaceClick = {
                            viewModel.selectPlace(it)
                            navController.navigate(MyCityScreen.Detail.name)
                        }
                    )
                }
            }
            composable(route = MyCityScreen.Detail.name) {
                uiState.selectedPlace?.let { place ->
                    PlaceDetailScreen(place = place)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyCityAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
private fun NavigationDrawerContent(
    selectedCategory: PlaceCategory,
    showSelection: Boolean,
    onCategoryClick: (PlaceCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.drawer_padding_header))
        )
        PlaceCategory.entries.forEach { category ->
            NavigationDrawerItem(
                selected = showSelection && selectedCategory == category,
                label = {
                    Text(
                        text = stringResource(category.title),
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(R.dimen.drawer_padding_header)
                        )
                    )
                },
                icon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = stringResource(category.title)
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onCategoryClick(category) }
            )
        }
    }
}
