package kz.mihael3d.rickandmortycharacters.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)

    @Query("SELECT * FROM locations")
    fun pagingSource(): PagingSource<Int,Location>

    @Query("DELETE FROM locations")
    suspend fun clearAll()

}