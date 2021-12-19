package kz.mihael3d.rickandmortycharacters.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kz.mihael3d.rickandmortycharacters.data.characters.remote.CharacterApi
import kz.mihael3d.rickandmortycharacters.data.characters.local.CharacterEntity

class CharactersPagingDataSourse()
//
//    (private val servise: CharacterApi) : PagingSource<Int, CharacterEntity>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
//        val pageNumber = params.key ?: 1
//       return  try {
//           val response = servise.getAllCharacters(pageNumber)
//           val pagedResponse = response.body()
//           val data = pagedResponse?.results
//
//           var nextPageNumber: Int? = null
//           if (pagedResponse?.pageInfo?.next != null) {
//               val uri = Uri.parse(pagedResponse.pageInfo.next)
//               val nextPageQuery = uri.getQueryParameter("page")
//               nextPageNumber = nextPageQuery?.toInt()
//           }
//
//           LoadResult.Page(
//               data = data.orEmpty(),
//               prevKey = null,
//               nextKey = nextPageNumber
//           )
//       } catch (e: Exception) {
//           LoadResult.Error(e)
//       }
//
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? = 1
//
////        return state.anchorPosition
//
//}