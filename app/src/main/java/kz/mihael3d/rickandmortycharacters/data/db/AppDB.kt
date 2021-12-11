package kz.mihael3d.rickandmortycharacters.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kz.mihael3d.rickandmortycharacters.data.db.convectors.StringConverter
import kz.mihael3d.rickandmortycharacters.data.db.dao.EpisodeDao
import kz.mihael3d.rickandmortycharacters.data.db.dao.EpisodePageKeyDao
import kz.mihael3d.rickandmortycharacters.data.db.dao.LocationDao
import kz.mihael3d.rickandmortycharacters.data.db.dao.LocationPageKeyDao
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.data.model.entites.EpisodePageKey
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location
import kz.mihael3d.rickandmortycharacters.data.model.entites.LocationPageKey

@Database(entities = [
    Episode::class, EpisodePageKey::class,
    Location::class, LocationPageKey::class
                     ], version = 2, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class AppDB: RoomDatabase() {

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