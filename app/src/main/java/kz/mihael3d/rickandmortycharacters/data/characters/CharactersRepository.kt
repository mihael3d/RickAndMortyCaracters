package kz.mihael3d.rickandmortycharacters.data.repository.charaters

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterEntity
import kz.mihael3d.rickandmortycharacters.data.characters.remote.CharacterApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.pagination.CharacterRemoteMediator
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.domain.characters.toCharacter
import kz.mihael3d.rickandmortycharacters.domain.locations.models.Location
import javax.inject.Inject

interface CharactersRepository {

    /**
     * @return Flow of PagingData [Character].
     */
    fun getAllCharactersWithCashing(): Flow<PagingData<Character>>
}


/**
 * Repository providing data about [Character]
 */
@ExperimentalPagingApi
class CharactersRepositoryImpl
@Inject constructor(
    private val service: CharacterApi,
    private val db: AppDB
) :
    CharactersRepository {

    override fun getAllCharactersWithCashing(): Flow<PagingData<Character>> =
        Pager( config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            remoteMediator = CharacterRemoteMediator( service, db,"Rick")
        ){
            db.charactersDao().getCharactersPagingSource(name="%Rick%")
        }.flow
            .map {paginData -> paginData
                .map {
                    characterEntity -> characterEntity.toCharacter()
                }
            }
    }