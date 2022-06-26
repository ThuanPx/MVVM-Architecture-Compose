package com.thuanpx.mvvm_architecture_compose.feature.home.component

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.thuanpx.mvvm_architecture_compose.base.ui.theme.DarkPurpleGray99
import com.thuanpx.mvvm_architecture_compose.model.entity.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by ThuanPx on 5/25/22.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClick: (name: String) -> Unit,
) {
    val cardBackgroundColor = remember {
        mutableStateOf(DarkPurpleGray99)
    }
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(pokemon.getImageUrl())
            .crossfade(true)
            .allowHardware(false)
            .build()
    )
    val painterState = painter.state

    if ((painterState is AsyncImagePainter.State.Success)) {
        LaunchedEffect(key1 = painter) {
            launch(Dispatchers.IO) {
                val image = painter.imageLoader.execute(painter.request).drawable ?: return@launch
                val palette = Palette.Builder(image.toBitmap()).generate()
                cardBackgroundColor.value = Color(palette.mutedSwatch?.rgb ?: return@launch)
            }
        }
    }
    Card(
        onClick = { onClick.invoke(pokemon.name ?: "") },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor.value),
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
            Image(
                painter = painter, contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = pokemon.name?.replaceFirstChar { it.uppercase() } ?: "",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
