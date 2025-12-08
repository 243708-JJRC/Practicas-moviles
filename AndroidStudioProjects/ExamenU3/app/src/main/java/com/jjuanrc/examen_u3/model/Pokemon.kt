package com.jjuanrc.examen_u3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val count: Int,
    val results: List<PokemonItem>
)

data class PokemonItem(
    val name: String,
    val url: String
) {
    fun getId(): Int {
        return url.split("/").dropLast(1).last().toInt()
    }
}

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeSlot>,
    val abilities: List<AbilitySlot>,
    val stats: List<StatSlot>
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?,
    val other: Other?
)

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class TypeSlot(
    val type: Type
)

data class Type(
    val name: String
)

data class AbilitySlot(
    val ability: Ability
)

data class Ability(
    val name: String
)

data class StatSlot(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)

@Entity(tableName = "favorite_pokemon")
data class FavoritePokemon(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String
)