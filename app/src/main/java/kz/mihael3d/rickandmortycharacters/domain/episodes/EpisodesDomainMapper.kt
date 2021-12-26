package kz.mihael3d.rickandmortycharacters.domain.episodes

import kz.mihael3d.rickandmortycharacters.data.episodes.local.EpisodeEntity
import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode

internal fun EpisodeEntity.toModel(): Episode = Episode(
    id = this.id,
    name = this.name,
    airDate = this.airDate,
    code = this.code,
    characters = this.characters,
    created = this.created
)