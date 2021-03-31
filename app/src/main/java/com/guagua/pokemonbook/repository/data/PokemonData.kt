package com.guagua.pokemonbook.repository.data

import com.guagua.model.Pokemon

data class PokemonData(
    var id: String?,
    var abilities: List<String>?,
    var attack: Int?,
    var base_exp: String?,
    var category: String?,
    var cycles: String?,
    var defense: Int?,
    var egg_groups: String?,
    var evolutions: List<String>?,
    var evolvedfrom: String?,
    var female_percentage: String?,
    var genderless: Int?,
    var height: String?,
    var hp: Int?,
    var imageurl: String?,
    var male_percentage: String?,
    var name: String?,
    var reason: String?,
    var special_attack: Int?,
    var special_defense: Int?,
    var speed: Int?,
    var total: Int?,
    var typeofpokemon: List<String>?,
    var weaknesses: List<String>?,
    var weight: String?,
    var xdescription: String?,
    var ydescription: String?
) {
    companion object {
        fun create(pokemon: Pokemon): PokemonData = with(pokemon) {
            PokemonData(id, abilities, attack, base_exp, category, cycles, defense, egg_groups, evolutions, evolvedfrom, female_percentage, genderless, height, hp, imageurl, male_percentage, name, reason, special_attack, special_defense, speed, total, typeofpokemon, weaknesses, weight, xdescription, ydescription)
        }
    }
}