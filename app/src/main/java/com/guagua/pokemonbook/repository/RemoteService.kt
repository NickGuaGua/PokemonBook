package com.guagua.pokemonbook.repository

import com.guagua.model.PokemonService
import javax.inject.Inject

class RemoteService @Inject constructor (private val pokemonService: PokemonService) {
    suspend fun getPokemonList() = pokemonService.get()
}