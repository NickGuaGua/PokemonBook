package com.guagua.model

import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    suspend fun get(): List<Pokemon>
}