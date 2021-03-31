package com.guagua.pokemonbook.ui.main

import android.os.Build
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guagua.pokemonbook.databinding.ViewItemGroupBinding
import com.guagua.pokemonbook.databinding.ViewListItemBinding
import com.guagua.pokemonbook.repository.data.ItemGroup
import com.guagua.pokemonbook.repository.data.PokemonData

class PokemonGroupAdapter: RecyclerView.Adapter<PokemonGroupViewHolder>() {

    var onItemClick: (pokemon: PokemonData) -> Unit = {}
    var onSeeMoreClick: (id: String) -> Unit = {}

    private val pokemonGroups = mutableListOf<ItemGroup<PokemonData>>()
    private val groupAdapters = mutableMapOf<String, PokemonAdapter>()
    private val saveInstanceStates = mutableMapOf<Long, Parcelable?>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonGroupViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonGroupViewHolder(
            ViewItemGroupBinding.inflate(layoutInflater, parent, false),
            onSeeMoreClick
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: PokemonGroupViewHolder, position: Int) {
        holder.bind(
            pokemonGroups[position],
            groupAdapters.getOrPut(pokemonGroups[position].id) {
                PokemonAdapter().apply { onClickCallback = onItemClick }
            },
            saveInstanceStates[holder.itemId]
        )
    }

    override fun onViewRecycled(holder: PokemonGroupViewHolder) {
        saveInstanceStates[holder.itemId] = holder.unbind()
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int = pokemonGroups.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(groups: List<ItemGroup<PokemonData>>) {
        pokemonGroups.clear()
        pokemonGroups.addAll(groups)
        notifyDataSetChanged()
    }
}

class PokemonGroupViewHolder(
    private val binding: ViewItemGroupBinding,
    private val onSeeMoreClick: (id: String) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pokemonGroup: ItemGroup<PokemonData>,
        itemsAdapter: PokemonAdapter,
        saveInstanceState: Parcelable?
    ) = with(binding) {
        title.text = pokemonGroup.title
        recyclerView.apply {
            adapter = itemsAdapter.apply { setData(pokemonGroup.items) }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                onRestoreInstanceState(saveInstanceState)
            }
        }
        seeMoreBtn.setOnClickListener { onSeeMoreClick(pokemonGroup.id) }
    }

    fun unbind(): Parcelable? {
        return binding.recyclerView.layoutManager?.onSaveInstanceState()
    }
}