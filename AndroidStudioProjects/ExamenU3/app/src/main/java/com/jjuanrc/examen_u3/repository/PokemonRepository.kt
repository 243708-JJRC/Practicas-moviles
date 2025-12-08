package com.jjuanrc.examen_u3.repository

import com.jjuanrc.examen_u3.api.Retrofit
import com.jjuanrc.examen_u3.database.PokemonDao
import com.jjuanrc.examen_u3.model.FavoritePokemon
import com.jjuanrc.examen_u3.model.PokemonDetail
import com.jjuanrc.examen_u3.model.PokemonItem
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val dao: PokemonDao) {

    suspend fun getPokemonList(): List<PokemonItem> {
        return try {
            Retrofit.api.getPokemonList().results
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPokemonDetail(id: Int): PokemonDetail? {
        return try {
            Retrofit.api.getPokemonDetail(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun searchPokemon(name: String): PokemonDetail? {
        return try {
            Retrofit.api.getPokemonByName(name.lowercase())
        } catch (e: Exception) {
            null
        }
    }

    fun getAllFavorites(): Flow<List<FavoritePokemon>> = dao.getAllFavorites()

    suspend fun isFavorite(id: Int): Boolean {
        return dao.getFavoriteById(id) != null
    }

    suspend fun addFavorite(pokemon: FavoritePokemon) {
        dao.insertFavorite(pokemon)
    }

    suspend fun removeFavorite(id: Int) {
        dao.deleteFavoriteById(id)
    }
}