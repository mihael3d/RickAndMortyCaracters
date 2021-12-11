package kz.mihael3d.rickandmortycharacters.data.model.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey val id: Int,
    val name: String,
    var page: Int?
)