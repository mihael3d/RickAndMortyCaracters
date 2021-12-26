package kz.mihael3d.rickandmortycharacters.data.episodes.pagination

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodePageKey")
data class EpisodePageKey(
    @PrimaryKey val id: Int,
    val nextPageUrl: String?
)