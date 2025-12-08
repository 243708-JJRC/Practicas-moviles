package com.jjuanrc.examen_u3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjuanrc.examen_u3.model.FavoritePokemon
import com.jjuanrc.examen_u3.model.PokemonDetail
import com.jjuanrc.examen_u3.model.PokemonItem
import com.jjuanrc.examen_u3.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonItem>>(emptyList())
    val pokemonList: StateFlow<List<PokemonItem>> = _pokemonList.asStateFlow()

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail.asStateFlow()

    private val _searchResult = MutableStateFlow<PokemonDetail?>(null)
    val searchResult: StateFlow<PokemonDetail?> = _searchResult.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    val favorites: StateFlow<List<FavoritePokemon>> = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadPokemonList()
    }

    fun loadPokemonList() {
        _isLoading.value = true
        viewModelScope.launch {
            val list = repository.getPokemonList()
            _pokemonList.value = list
            _isLoading.value = false
        }
    }

    fun loadPokemonDetail(id: Int) {
        viewModelScope.launch {
            val detail = repository.getPokemonDetail(id)
            _pokemonDetail.value = detail
            checkIfFavorite(id)
        }
    }

    fun searchPokemon(query: String) {
        viewModelScope.launch {
            val result = repository.searchPokemon(query)
            _searchResult.value = result
        }
    }

    private fun checkIfFavorite(id: Int) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(id)
        }
    }

    fun toggleFavorite(pokemon: FavoritePokemon) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.removeFavorite(pokemon.id)
                _isFavorite.value = false
            } else {
                repository.addFavorite(pokemon)
                _isFavorite.value = true
            }
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            repository.removeFavorite(id)
        }
    }
}
