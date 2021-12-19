package kz.mihael3d.rickandmortycharacters.data.characters.local

import androidx.room.Embedded
import kz.mihael3d.rickandmortycharacters.data.characters.remote.CharacterDto

internal fun CharacterDto.toEntity(): CharacterEntity = CharacterEntity(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    image = this.image,
    url = this.url,
    locationName = this.location.name,
    locationUrl = this.location.url,
    originName = this.origin.name,
    originUrl = this.origin.url,
    episode = this.episode
)