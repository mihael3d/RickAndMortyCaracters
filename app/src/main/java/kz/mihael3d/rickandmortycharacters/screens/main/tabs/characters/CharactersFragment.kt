package kz.mihael3d.rickandmortycharacters.screens.main.tabs.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStateAtLeast
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.data.model.Character
import kz.mihael3d.rickandmortycharacters.databinding.FragmentCharactersBinding
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterBinding
import kz.mihael3d.rickandmortycharacters.screens.main.tabs.TabsFragmentDirections
import kz.mihael3d.rickandmortycharacters.utils.findTopNavController
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters), CharactersAdapter.CharacterClickListener {
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

         findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment2(character),
        extras)
//         findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToCharacterDetailFragment(character),
//        extras)
    }
}