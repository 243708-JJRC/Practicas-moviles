package com.jjuanrc.examen_u3.database

import androidx.room.*
import com.jjuanrc.examen_u3.model.FavoritePokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM favorite_pokemon ORDER BY name ASC")
    fun getAllFavorites(): Flow<List<FavoritePokemon>>

    @Query("SELECT * FROM favorite_pokemon WHERE id = :id")
    suspend fun getFavoriteById(id: Int): FavoritePokemon?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokemon: FavoritePokemon)

    @Delete
    suspend fun deleteFavorite(pokemon: FavoritePokemon)

    @Query("DELETE FROM favorite_pokemon WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)
}