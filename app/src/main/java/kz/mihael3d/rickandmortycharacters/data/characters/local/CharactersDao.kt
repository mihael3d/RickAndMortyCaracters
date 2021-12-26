package kz.mihael3d.rickandmortycharacters.data.characters.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterEntity

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CharacterEntity>)

//    @Query("""SELECT *
//        FROM ${CharacterEntity.TABLE_NAME}
//        WHERE ${CharacterEntity.CHARACTER_NAME} LIKE  '%' || :name || '%'
//        ORDER BY ${CharacterEntity.CHARACTER_ID}  ASC
//        """)
//    fun getCharactersByNamePagingSource(name: String): PagingSource<Int, CharacterEntity>


    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}  WHERE ${CharacterEntity.CHARACTER_NAME} LIKE  :name ORDER BY ${CharacterEntity.CHARACTER_ID}  ASC")
    fun getCharactersByNamePagingSource(name: String): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}  WHERE ${CharacterEntity.CHARACTER_ID} LIKE '%' ||:id ||'%' ORDER BY ${CharacterEntity.CHARACTER_ID}  ASC")
    fun getCharactersByIdPagingSource(id: Int): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}")
    fun getCharactersPagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE  FROM characters")
    suspend fun clearAll()
}
