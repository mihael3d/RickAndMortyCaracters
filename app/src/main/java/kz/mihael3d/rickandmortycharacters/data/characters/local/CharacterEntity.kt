package kz.mihael3d.rickandmortycharacters.data.characters.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kz.mihael3d.rickandmortycharacters.data.characters.Gender
import kz.mihael3d.rickandmortycharacters.data.model.Status

@Parcelize
@Entity(tableName = CharacterEntity.TABLE_NAME)
data class CharacterEntity(

    @PrimaryKey
    @ColumnInfo(name = CHARACTER_ID)
    val id: Int,

    @ColumnInfo(name = CHARACTER_NAME)
    val name: String,

    @ColumnInfo(name = CHARACTER_STATUS)
    val status: Status,

    @ColumnInfo(name = CHARACTER_SPECIES)
    val species: String,

    @ColumnInfo(name = CHARACTER_TYPE)
    val type: String,

    @ColumnInfo(name = CHARACTER_GENDER)
    val gender: Gender,

    @ColumnInfo(name = CHARACTER_IMAGE)
    val image: String,

    @ColumnInfo(name = CHARACTER_URL)
    val url: String,

    @ColumnInfo(name = CHARACTER_LOCATION_NAME)
    val locationName: String,

    @ColumnInfo(name = CHARACTER_LOCATION_URL)
    val locationUrl: String,

    @ColumnInfo(name = CHARACTER_ORIGIN_NAME)
    val originName: String,

    @ColumnInfo(name = CHARACTER_ORIGIN_URL)
    val originUrl: String,

    @ColumnInfo(name = CHARACTER_EPISODES)
    val episode: List<String>,

    @ColumnInfo(name = CHARACTER_PAGING_PAGE)
    var page: Int? = null
) : Parcelable {

    companion object {
        const val TABLE_NAME = "characters"

        const val CHARACTER_ID = "character_id"
        const val CHARACTER_NAME = "character_name"
        const val CHARACTER_STATUS = "character_status"
        const val CHARACTER_SPECIES = "character_species"
        const val CHARACTER_TYPE = "character_type"
        const val CHARACTER_GENDER = "character_gender"
        const val CHARACTER_IMAGE = "character_image"
        const val CHARACTER_URL = "character_url"
        const val CHARACTER_LOCATION_NAME = "character_location_name"
        const val CHARACTER_LOCATION_URL = "character_location_url"
        const val CHARACTER_ORIGIN_NAME = "character_origin_name"
        const val CHARACTER_ORIGIN_URL = "character_origin_url"
        const val CHARACTER_EPISODES = "character_episodes"
        const val CHARACTER_PAGING_PAGE = "character_paging_page"

    }
}


