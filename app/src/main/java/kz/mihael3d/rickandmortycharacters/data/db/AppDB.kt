package kz.mihael3d.rickandmortycharacters.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharactersPageKey
import kz.mihael3d.rickandmortycharacters.data.db.convectors.CharacterStatusTypeConvertor
import kz.mihael3d.rickandmortycharacters.data.db.convectors.StringConverter
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterEntity
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharactersDao
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharactersPageKeyDao
import kz.mihael3d.rickandmortycharacters.data.episodes.local.EpisodeEntity
import kz.mihael3d.rickandmortycharacters.data.episodes.local.EpisodeDao
import kz.mihael3d.rickandmortycharacters.data.episodes.pagination.EpisodePageKey
import kz.mihael3d.rickandmortycharacters.data.episodes.pagination.EpisodePageKeyDao
import kz.mihael3d.rickandmortycharacters.data.locations.local.*

@Database(entities = [
    CharacterEntity::class, CharactersPageKey::class,
    EpisodeEntity::class, EpisodePageKey::class,
    LocationEntity::class, LocationPageKey::class
                     ], version = 1, exportSchema = false)
@TypeConverters(
    StringConverter::class,
    CharacterStatusTypeConvertor::class
    )
abstract class AppDB: RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun charactersPageKeyDao(): CharactersPageKeyDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun episodePageKeyDao(): EpisodePageKeyDao
    abstract fun locationDao(): LocationDao
    abstract fun locationPageKeyDao(): LocationPageKeyDao


    companion object{
        @Volatile private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB =
            instance ?: synchronized(this){
                instance ?: buildDataBase(context)
            }

        private fun buildDataBase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDB::class.java, "rickandmortycharacters")
                .fallbackToDestructiveMigration()
                .build()
    }
}