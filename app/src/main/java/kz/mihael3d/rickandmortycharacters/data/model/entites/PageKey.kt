package kz.mihael3d.rickandmortycharacters.data.model.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pageKey")
data class PageKey(
    @PrimaryKey val id: Int,
    val nextPageUrl: String?
)