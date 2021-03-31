package com.guagua.pokemonbook.ui.main.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import com.guagua.pokemonbook.databinding.ViewItemGroupHeaderBinding
import com.guagua.pokemonbook.databinding.ViewListItemBinding
import com.guagua.pokemonbook.repository.data.ItemGroup
import com.guagua.pokemonbook.repository.data.PokemonData

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class PokemonItemModelView: FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    )

    private val binding = ViewListItemBinding.inflate(LayoutInflater.from(context), this, true)

    @ModelProp
    fun setData(pokemonData: PokemonData) = with(binding) {
        name.text = pokemonData.name
        Glide.with(image).load(pokemonData.imageurl).into(image)
    }

    @ModelProp(ModelProp.Option.DoNotHash)
    fun setOnItemClick(l: OnClickListener) {
        super.setOnClickListener(l)
    }
}