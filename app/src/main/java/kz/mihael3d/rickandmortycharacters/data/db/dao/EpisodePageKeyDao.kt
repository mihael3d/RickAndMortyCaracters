package kz.mihael3d.rickandmortycharacters.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.mihael3d.rickandmortycharacters.data.model.entites.EpisodePageKey

@Dao
interface EpisodePageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(episodePageKey: EpisodePageKey)

    @Query("SELECT * FROM episodePageKey WHERE id Like :id")
    fun getNextPageKey(id: Int): EpisodePageKey?

    @Query("DELETE FROM episodePageKey")
    suspend fun clearAll()
}


