package kz.mihael3d.rickandmortycharacters.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext appContext: Context) = AppDB.getDatabase(appContext)
}