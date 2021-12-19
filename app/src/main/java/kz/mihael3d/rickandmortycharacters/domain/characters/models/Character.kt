package kz.mihael3d.rickandmortycharacters.domain.characters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kz.mihael3d.rickandmortycharacters.data.characters.Gender
import kz.mihael3d.rickandmortycharacters.data.model.Status

/**
 * Business class of Character
 */
@Parcelize
class Character(
    val id: Int,
    val name: String,
    val species: String,
    val status: Status,
    val type: String,
    val gender: Gender,
    val image: String,
    val url: String,
    val locationName: String,
    val locationUrl: String,
    val originName: String,
    val originUrl: String,
    val episode: List<String>,
) : Parcelable
