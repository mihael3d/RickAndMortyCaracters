package kz.mihael3d.rickandmortycharacters.data.repository.charaters

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kz.mihael3d.rickandmortycharacters.data.characters.remote.CharacterApi
import kz.mihael3d.rickandmortycharacters.data.db.AppDB
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterRemoteMediator
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.domain.characters.toCharacter
import javax.inject.Inject

interface CharactersRepository {

    /**
     * @return Flow of PagingData [Character].
     */
    fun getCharactersByNameWithCashing(name: String): Flow<PagingData<Character>>

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

    override fun getCharactersByNameWithCashing(name: String): Flow<PagingData<Character>> =
        Pager( config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            remoteMediator = CharacterRemoteMediator( service, db, name)

        ){
            db.charactersDao().getCharactersByNamePagingSource("%" + name + "%")
        }.flow
            .map {paginData -> paginData
                .map {
                    characterEntity -> characterEntity.toCharacter()
                }
            }

    override fun getAllCharactersWithCashing(): Flow<PagingData<Character>> =
        Pager( config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            remoteMediator = CharacterRemoteMediator( service, db )

        ){
            db.charactersDao().getCharactersPagingSource()
        }.flow
            .map { paginData ->
                paginData
                    .map { characterEntity ->
                        characterEntity.toCharacter()

                    }
            }
}