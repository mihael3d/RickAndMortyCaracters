package kz.mihael3d.rickandmortycharacters.data.episodes


import kz.mihael3d.rickandmortycharacters.data.episodes.local.EpisodeEntity
import kz.mihael3d.rickandmortycharacters.data.episodes.remote.EpisodeDto

internal fun EpisodeDto.toEntity(): EpisodeEntity = EpisodeEntity(
    id = this.id,
    name = this.name,
    airDate = this.airDate,
    code = this.episode,
    characters = this.characters,
    created = this.created
)