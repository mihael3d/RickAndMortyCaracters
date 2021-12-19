package kz.mihael3d.rickandmortycharacters.domain.characters

import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterEntity
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character


internal fun CharacterEntity.toCharacter() = Character(
    id = this.id,
    name = this.name,
    species = this.species,
    status = this.status,
    type = this.type,
    gender = this.gender,
    image = this.image,
    url = this.url,
    locationName = this.locationName,
    locationUrl = this.locationUrl,
    originName = this.originName,
    originUrl = this.originUrl,
    episode=this.episode
)