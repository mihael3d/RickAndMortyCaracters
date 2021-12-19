package kz.mihael3d.rickandmortycharacters.data.locations.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DB class of Location stored in room
 */
@Entity(tableName = LocationEntity.TABLE_NAME)
data class LocationEntity(

    @PrimaryKey
    @ColumnInfo(name = LOCATION_ID)
    val id: Int,

    @ColumnInfo(name = LOCATION_NAME)
    val name: String,

    @ColumnInfo(name = LOCATION_TYPE)
    val type: String,

    @ColumnInfo(name = LOCATION_DIMENSION)
    val dimension: String,

    @ColumnInfo(name = LOCATION_RESIDENTS)
    val residents: List<String>,

    @ColumnInfo(name = LOCATION_CREATED)
    val created: String,

    @ColumnInfo(name = LOCATION_PAGE)
    var page: Int? = 1
){

    companion object {
        const val TABLE_NAME = "locations"

        const val LOCATION_ID = "location_id"
        const val LOCATION_NAME = "location_name"
        const val LOCATION_TYPE = "location_type"
        const val LOCATION_DIMENSION = "location_dimension"
        const val LOCATION_RESIDENTS = "location_residents"
        const val LOCATION_CREATED = "location_created"
        const val LOCATION_PAGE = "location_page"
    }
}

