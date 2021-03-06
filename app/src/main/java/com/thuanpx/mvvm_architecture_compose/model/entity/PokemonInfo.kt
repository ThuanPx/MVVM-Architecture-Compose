package com.thuanpx.mvvm_architecture_compose.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by ThuanPx on 8/13/20.
 */
data class PokemonInfo(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("weight")
    val weight: Int? = null,
) {
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
