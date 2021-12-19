package kz.mihael3d.rickandmortycharacters.data.pagination

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characterPageKey")
class CharactersPageKey(
    @PrimaryKey val id: Int,
    val nextPageUrl: String?
)