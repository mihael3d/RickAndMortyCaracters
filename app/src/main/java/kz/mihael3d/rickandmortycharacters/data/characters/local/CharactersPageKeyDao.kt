package kz.mihael3d.rickandmortycharacters.data.characters.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersPageKeyDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace (charactersPageKey: CharactersPageKey)

    @Query("SELECT * FROM characterPageKey WHERE id LIKE :id")
    fun getNextPageKey(id:Int) : CharactersPageKey

    @Query("DELETE FROM characterPageKey")
    suspend fun clearAll()

}