package kz.mihael3d.rickandmortycharacters.di

import androidx.paging.ExperimentalPagingApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.mihael3d.rickandmortycharacters.data.repository.charaters.CharactersRepository
import kz.mihael3d.rickandmortycharacters.data.repository.charaters.CharactersRepositoryImpl
import kz.mihael3d.rickandmortycharacters.data.repository.episodes.EpisodesERepositoryImpl
import kz.mihael3d.rickandmortycharacters.data.repository.episodes.EpisodesRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharactersRepository(
        characterRepositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository


    @ExperimentalPagingApi
    @Binds
    abstract fun bindEpisodesRepository(
        episodesRepository: EpisodesERepositoryImpl
    ) : EpisodesRepository

}