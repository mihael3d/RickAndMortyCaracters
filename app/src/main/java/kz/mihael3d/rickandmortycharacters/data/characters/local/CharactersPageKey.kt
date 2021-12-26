package kz.mihael3d.rickandmortycharacters.data.characters.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characterPageKey")
class CharactersPageKey(
    @PrimaryKey val id: Int,
    val nextPageUrl: String?
)