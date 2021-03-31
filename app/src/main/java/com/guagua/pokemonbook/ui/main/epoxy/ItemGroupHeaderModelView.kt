package com.guagua.pokemonbook.ui.main.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.guagua.pokemonbook.databinding.ViewItemGroupHeaderBinding
import com.guagua.pokemonbook.repository.data.ItemGroup

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ItemGroupHeaderModelView: FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    )

    private val binding = ViewItemGroupHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    @ModelProp
    fun setItemGroup(itemGroup: ItemGroup<*>) {
        binding.title.text = itemGroup.title
    }

    @ModelProp(ModelProp.Option.DoNotHash)
    fun setOnSeeMoreClick(listener: () -> Unit) {
        binding.seeMoreBtn.setOnClickListener { listener.invoke() }
    }
}