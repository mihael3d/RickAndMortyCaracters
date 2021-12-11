package kz.mihael3d.rickandmortycharacters.data.model.entites

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "locationPageKey")
data class LocationPageKey(
    @PrimaryKey val id: Int,
    val nextPageUrl: String?
)