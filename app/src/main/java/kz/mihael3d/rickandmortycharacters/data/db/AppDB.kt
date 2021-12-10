package kz.mihael3d.rickandmortycharacters.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.hilt.android.qualifiers.ApplicationContext
import kz.mihael3d.rickandmortycharacters.data.db.convectors.StringConverter
import kz.mihael3d.rickandmortycharacters.data.db.dao.EpisodeDao
import kz.mihael3d.rickandmortycharacters.data.db.dao.PageKeyDao
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.data.model.entites.PageKey

@Database(entities = [Episode::class, PageKey::class], version = 1, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class AppDB: RoomDatabase() {

    abstract fun episodeDao(): EpisodeDao
    abstract fun pageKeyDao(): PageKeyDao


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