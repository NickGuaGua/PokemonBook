package com.guagua.pokemonbook.di

import com.guagua.model.APIService
import com.guagua.model.PokemonService
import com.guagua.pokemonbook.repository.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePokemonService(): PokemonService = APIService.pokemonService

    @Singleton
    @Provides
    fun provideRemoteService(pokemonService: PokemonService): RemoteService = RemoteService(pokemonService)
}