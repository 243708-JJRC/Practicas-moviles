package com.jjuanrc.examen_u3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jjuanrc.examen_u3.database.PokemonDatabase
import com.jjuanrc.examen_u3.navigation.PokemonNavigation
import com.jjuanrc.examen_u3.repository.PokemonRepository
import com.jjuanrc.examen_u3.ui.theme.PokemonTheme
import com.jjuanrc.examen_u3.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = PokemonDatabase.getDatabase(this).pokemonDao()
        val repository = PokemonRepository(dao)
        viewModel = PokemonViewModel(repository)

        setContent {
            PokemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonNavigation(viewModel)
                }
            }
        }
    }
}