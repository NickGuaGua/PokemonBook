package com.guagua.pokemonbook.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guagua.pokemonbook.databinding.MainFragmentBinding
import com.guagua.pokemonbook.repository.data.ItemGroup
import com.guagua.pokemonbook.repository.data.PokemonData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val nativeGroupAdapter: PokemonGroupAdapter by lazy {
        PokemonGroupAdapter().apply {
            onSeeMoreClick = {
                toast("on see more click: group id -> $it")
            }
            onItemClick = {
                toast("on pokemon click: ${it.id} -> ${it.name}")
            }
        }
    }

    private val epoxyMainController: MainController by lazy {
        MainController(object : MainController.Callback {
            override fun onSeeMoreClick(groupId: String) {
                toast("on see more click: group id -> $groupId")
            }

            override fun onItemClick(item: PokemonData) {
                toast("on pokemon click: ${item.id} -> ${item.name}")
            }
        })
    }

    private val stateObserver: Observer<MainViewModel.State> = Observer {
        when(it) {
            is MainViewModel.State.Loading -> {}
            is MainViewModel.State.Success -> {
                setupAdapterWithNative(it.pokemonGroups)
                setupAdapterWithEpoxy(it.pokemonGroups)
            }
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, stateObserver)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        binding.recyclerView.adapter = adapter
    }

    private fun setupAdapterWithNative(groups: List<ItemGroup<PokemonData>>) {
        setAdapter(nativeGroupAdapter.apply {
            setHasStableIds(true)
            setData(groups)
        })
    }

    private fun setupAdapterWithEpoxy(groups: List<ItemGroup<PokemonData>>) {
        setAdapter(epoxyMainController.adapter)
        epoxyMainController.setData(groups)
    }

    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}