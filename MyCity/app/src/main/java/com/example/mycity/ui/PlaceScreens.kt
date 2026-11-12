package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.mycity.R
import com.example.mycity.data.Place

@Composable
fun PlaceListScreen(
    places: List<Place>,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensionResource(R.dimen.place_card_min_width)),
        modifier = modifier,
        contentPadding = PaddingValues(
            dimensionResource(R.dimen.padding_medium)
        ),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(places, key = { place -> place.id }) { place ->
            PlaceCard(
                place = place,
                selected = false,
                onClick = { onPlaceClick(place) }
            )
        }
    }
}

@Composable
fun PlaceListAndDetailScreen(
    places: List<Place>,
    selectedPlace: Place?,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                dimensionResource(R.dimen.padding_medium)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            items(places, key = { place -> place.id }) { place ->
                PlaceCard(
                    place = place,
                    selected = place.id == selectedPlace?.id,
                    onClick = { onPlaceClick(place) }
                )
            }
        }
        if (selectedPlace != null) {
            PlaceDetailScreen(
                place = selectedPlace,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PlaceDetailScreen(
    place: Place,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Image(
            painter = painterResource(place.image),
            contentDescription = stringResource(place.name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(PLACE_IMAGE_ASPECT_RATIO)
                .clip(MaterialTheme.shapes.large)
        )
        Text(
            text = stringResource(place.name),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(R.string.place_rating, place.rating.toString()),
            style = MaterialTheme.typography.titleMedium
        )
        place.description?.let { description ->
            PlaceInfoSection(
                label = stringResource(R.string.place_details_label),
                value = stringResource(description)
            )
        }
        PlaceInfoSection(
            label = stringResource(R.string.place_address_label),
            value = stringResource(place.address)
        )
        place.phone?.let { phone ->
            PlaceInfoSection(
                label = stringResource(R.string.place_phone_label),
                value = stringResource(phone)
            )
        }
        place.website?.let { website ->
            PlaceInfoSection(
                label = stringResource(R.string.place_website_label),
                value = stringResource(website)
            )
        }
    }
}

@Composable
private fun PlaceInfoSection(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun PlaceCard(
    place: Place,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        )
    ) {
        Column {
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(PLACE_IMAGE_ASPECT_RATIO)
            )
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = stringResource(place.name),
                    style = MaterialTheme.typography.titleLarge
                )
                place.description?.let { description ->
                    Text(
                        text = stringResource(description),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = stringResource(place.address),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private const val PLACE_IMAGE_ASPECT_RATIO = 1.6f
