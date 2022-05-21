package com.thuanpx.mvvm_architecture_compose.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by ThuanPx on 8/13/20.
 */
data class Pokemon(
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
) {
    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}
