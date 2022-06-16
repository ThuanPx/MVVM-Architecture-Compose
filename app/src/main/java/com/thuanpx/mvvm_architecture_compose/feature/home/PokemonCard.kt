package com.thuanpx.mvvm_architecture_compose.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon

/**
 * Created by ThuanPx on 5/25/22.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick.invoke(pokemon.name ?: "") },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier
            .padding(5.dp)
            .size(150.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.getImageUrl())
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = pokemon.name?.replaceFirstChar { it.uppercase() } ?: "",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}