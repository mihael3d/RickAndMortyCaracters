package kz.mihael3d.rickandmortycharacters.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.mihael3d.rickandmortycharacters.data.locations.local.LocationPageKey

@Dao
interface LocationPageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(locationPageKey: LocationPageKey)

    @Query("SELECT * FROM locationPageKey WHERE id like :id")
    fun getNextPageKey(id: Int): LocationPageKey?

    @Query("DELETE FROM locationPageKey")
    suspend fun clearAll()
}