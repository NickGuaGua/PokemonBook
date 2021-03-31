package com.guagua.pokemonbook.ui.main

import androidx.lifecycle.*
import com.guagua.pokemonbook.repository.Repository
import com.guagua.pokemonbook.repository.data.ItemGroup
import com.guagua.pokemonbook.repository.data.PokemonData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: Repository
): ViewModel(), LifecycleObserver {

    private val _state = MutableLiveData<State>(State.IDLE)
    val state: LiveData<State> = _state

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        viewModelScope.launch {
            setState(State.Success(repository.getPokemonGroups()))
        }
    }

    private fun setState(state: State) {
        _state.value = state
    }

    sealed class State {
        object IDLE: State()
        object Loading: State()
        data class Success(val pokemonGroups: List<ItemGroup<PokemonData>>): State()
    }
}