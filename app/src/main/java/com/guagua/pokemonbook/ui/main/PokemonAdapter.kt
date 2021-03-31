package com.guagua.pokemonbook.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guagua.pokemonbook.databinding.ViewListItemBinding
import com.guagua.pokemonbook.repository.data.PokemonData

class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder>() {

    var onClickCallback: (pokemon: PokemonData) -> Unit = {}

    private val pokemonDataList = mutableListOf<PokemonData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(
            ViewListItemBinding.inflate(layoutInflater, parent, false),
            onClickCallback
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonDataList[position])
    }

    override fun getItemCount(): Int = pokemonDataList.size

    fun setData(pokemons: List<PokemonData>) {
        pokemonDataList.clear()
        pokemonDataList.addAll(pokemons)
        notifyDataSetChanged()
    }
}

class PokemonViewHolder(
    private val binding: ViewListItemBinding,
    private val onClickListener: (pokemon: PokemonData) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemonData: PokemonData) = with(binding) {
        Glide.with(image).load(pokemonData.imageurl).into(image)
        name.text = pokemonData.name
        binding.root.setOnClickListener { onClickListener(pokemonData) }
    }
}