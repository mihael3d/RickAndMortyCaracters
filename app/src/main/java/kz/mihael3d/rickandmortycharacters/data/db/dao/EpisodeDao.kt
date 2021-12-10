package kz.mihael3d.rickandmortycharacters.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<Episode>)

    @Query("SELECT * FROM episodes")
    fun getEpisodesPagingSource(): PagingSource<Int, Episode>

    @Query("DELETE FROM episodes")
    suspend fun clearAll()

}