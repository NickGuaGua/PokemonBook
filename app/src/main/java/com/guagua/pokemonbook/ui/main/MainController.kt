package com.guagua.pokemonbook.ui.main

import android.telecom.Call
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.guagua.pokemonbook.R
import com.guagua.pokemonbook.repository.data.ItemGroup
import com.guagua.pokemonbook.repository.data.PokemonData
import com.guagua.pokemonbook.ui.main.epoxy.PokemonItemModelViewModel_
import com.guagua.pokemonbook.ui.main.epoxy.itemGroupHeaderModelView

class MainController (
    private val callback: Callback
): TypedEpoxyController<List<ItemGroup<PokemonData>>>() {

    interface Callback {
        fun onSeeMoreClick(groupId: String)
        fun onItemClick(item: PokemonData)
    }

    override fun buildModels(data: List<ItemGroup<PokemonData>>?) {
        data?.forEach {
            group {
                id(it.id)
                layout(R.layout.view_epoxy_item_group)

                itemGroupHeaderModelView {
                    id("${it.id}_header")
                    itemGroup(it)
                    onSeeMoreClick { callback.onSeeMoreClick(it.id) }
                }

                carousel {
                    id("${it.id}_carousel")
                    shouldSaveViewState(true)
                    models(it.items.map {
                        PokemonItemModelViewModel_()
                            .id(it.id)
                            .data(it)
                            .onItemClick { _ -> callback.onItemClick(it) }
                    })
                }
            }
        }
    }
}