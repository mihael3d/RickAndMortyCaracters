package kz.mihael3d.rickandmortycharacters.data.episodes.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodeEntities: List<EpisodeEntity>)

    @Query("SELECT * FROM episodes")
    fun getEpisodesPagingSource(): PagingSource<Int, EpisodeEntity>

    @Query("DELETE FROM episodes")
    suspend fun clearAll()

}