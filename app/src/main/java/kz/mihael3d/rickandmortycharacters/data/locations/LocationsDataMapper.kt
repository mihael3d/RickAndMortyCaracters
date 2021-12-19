package kz.mihael3d.rickandmortycharacters.data.locations.local


import kz.mihael3d.rickandmortycharacters.data.locations.remote.LocationDto

internal fun LocationDto.toEntity():  LocationEntity = LocationEntity(
    id = this.id,
    name = this.name,
    type = this.type,
    dimension = this.dimension,
    residents = this.residents,
    created = this.created
)