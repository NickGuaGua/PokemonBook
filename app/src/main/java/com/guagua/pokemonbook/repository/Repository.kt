package com.guagua.pokemonbook.repository

import com.guagua.pokemonbook.repository.data.ItemGroup
import com.guagua.pokemonbook.repository.data.PokemonData
import javax.inject.Inject

class Repository @Inject constructor (private val remoteService: RemoteService) {

    private val pokemonDataList = mutableListOf<PokemonData>()

    suspend fun getPokemonList(): List<PokemonData> {
        return if (pokemonDataList.isNotEmpty()) pokemonDataList
        else remoteService.getPokemonList().map {
            PokemonData.create(it)
        }.also {
            pokemonDataList.clear()
            pokemonDataList.addAll(it)
        }
    }

    suspend fun getPokemonGroups(): List<ItemGroup<PokemonData>> {
        return getPokemonList().groupBy { it.typeofpokemon }.map {
            ItemGroup(
                id = "${it.key}_group",
                title = it.key.toString(),
                items = it.value
            )
        }
    }
}