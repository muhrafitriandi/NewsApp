package com.yandey.designsystem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> FilterChipGroup(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (T) -> Unit
) {
    LazyRow(
        modifier = modifier.padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items) { item ->
            FilterChip(
                selected = item == selectedItem,
                onClick = { onItemSelected(item) },
                label = { label(item) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}
