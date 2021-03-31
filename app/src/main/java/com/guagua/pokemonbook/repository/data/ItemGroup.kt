package com.guagua.pokemonbook.repository.data

data class ItemGroup<T>(val id: String, val title: String, val items: List<T>)