package kz.mihael3d.rickandmortycharacters.data.episodes.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DB class of Location stored in room
 */
@Entity(tableName = EpisodeEntity.TABLE_NAME)
data class EpisodeEntity(

    @PrimaryKey
    @ColumnInfo(name = EPISODE_ID)
    val id: Int,

    @ColumnInfo(name = EPISODE_NAME)
    val name: String,

    @ColumnInfo(name = EPISODE_AIR_DATE)
    val airDate: String,

    @ColumnInfo(name = EPISODE_CODE)
    val code: String,

    @ColumnInfo(name = EPISODE_CHARACTERS)
    val characters: List<String>,

    @ColumnInfo(name = EPISODE_CREATED)
    val created: String,

    @ColumnInfo(name = EPISODE_PAGE)
    var page: Int? = null
){
    companion object {
        const val TABLE_NAME = "episodes"

        const val EPISODE_ID = "episode_id"
        const val EPISODE_NAME = "episode_name"
        const val EPISODE_AIR_DATE = "episode_air_date"
        const val EPISODE_CODE = "episode_code"
        const val EPISODE_CHARACTERS = "episode_characters"
        const val EPISODE_CREATED = "episode_created"
        const val EPISODE_PAGE = "episode_page"
    }
}