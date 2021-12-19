package kz.mihael3d.rickandmortycharacters.presentation.character.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentCharactersBinding
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterBinding
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.presentation.PagingLoadStateAdapter
import kz.mihael3d.rickandmortycharacters.presentation.character.viewmodel.CharactersViewModel
import kz.mihael3d.rickandmortycharacters.utils.hideKeyboard
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters),
    CharactersAdapter.CharacterClickListener {
    private lateinit var bindin: FragmentCharactersBinding

    private val charactersViewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var charactersAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindin = FragmentCharactersBinding.bind(view)

        with(bindin){
            with(charactersAdapter){
                rvCharacters.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                    addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                                recyclerView.hideKeyboard()
                            }
                        }
                    })
                }
                rvCharacters.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                    )
                swipeRefresh.setOnRefreshListener { refresh() }
                characterClickListener = this@CharactersFragment

                with(charactersViewModel){
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                        charcersFlow.collectLatest { charactersAdapter.submitData(it) }
                    }
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading }

                    }
                }
            }
        }
    }

     override fun onCharacterClickListener(binding: ItemCharacterBinding, character: Character) {
         val extras = FragmentNavigatorExtras(
             binding.ivAvatar to "avatar_${character.id}",
             binding.tvName to "name_${character.id}"
         )

         findNavController().navigate(
             CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment2(character),
        extras)
    }
}