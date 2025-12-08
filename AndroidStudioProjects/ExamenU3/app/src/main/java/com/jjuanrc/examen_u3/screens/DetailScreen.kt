package com.jjuanrc.examen_u3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jjuanrc.examen_u3.model.FavoritePokemon
import com.jjuanrc.examen_u3.viewmodel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: PokemonViewModel,
    pokemonId: Int,
    pokemonName: String,
    onBackClick: () -> Unit
) {
    val pokemonDetail by viewModel.pokemonDetail.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()

    LaunchedEffect(pokemonId) {
        viewModel.loadPokemonDetail(pokemonId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemonName.replaceFirstChar { it.uppercase() }) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            pokemonDetail?.let { detail ->
                FloatingActionButton(
                    onClick = {
                        val imageUrl = detail.sprites.other?.officialArtwork?.frontDefault
                            ?: detail.sprites.frontDefault ?: ""
                        val favPokemon = FavoritePokemon(
                            id = detail.id,
                            name = detail.name,
                            imageUrl = imageUrl
                        )
                        viewModel.toggleFavorite(favPokemon)
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito"
                    )
                }
            }
        }
    ) { padding ->
        pokemonDetail?.let { detail ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageUrl = detail.sprites.other?.officialArtwork?.frontDefault
                    ?: detail.sprites.frontDefault

                AsyncImage(
                    model = imageUrl,
                    contentDescription = detail.name,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = detail.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "#${detail.id.toString().padStart(3, '0')}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                InfoCard(
                    title = "Información Básica",
                    content = {
                        InfoRow("Altura", "${detail.height / 10.0} m")
                        InfoRow("Peso", "${detail.weight / 10.0} kg")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoCard(
                    title = "Tipos",
                    content = {
                        Text(
                            text = detail.types.joinToString(", ") {
                                it.type.name.replaceFirstChar { c -> c.uppercase() }
                            }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoCard(
                    title = "Habilidades",
                    content = {
                        Text(
                            text = detail.abilities.joinToString(", ") {
                                it.ability.name.replaceFirstChar { c -> c.uppercase() }
                            }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoCard(
                    title = "Estadísticas",
                    content = {
                        detail.stats.forEach { stat ->
                            InfoRow(
                                label = stat.stat.name.replaceFirstChar { it.uppercase() },
                                value = stat.baseStat.toString()
                            )
                        }
                    }
                )
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun InfoCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
