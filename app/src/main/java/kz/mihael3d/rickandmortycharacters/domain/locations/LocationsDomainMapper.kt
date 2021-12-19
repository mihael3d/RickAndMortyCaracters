package kz.mihael3d.rickandmortycharacters.domain.locations

import kz.mihael3d.rickandmortycharacters.data.locations.local.LocationEntity
import kz.mihael3d.rickandmortycharacters.domain.locations.models.Location

internal fun LocationEntity.toLocation() = Location(
    id = this.id,
    name = this.name,
    type = this.type,
    dimension = this.dimension,
    residents = this.residents,
    created = this.created
)