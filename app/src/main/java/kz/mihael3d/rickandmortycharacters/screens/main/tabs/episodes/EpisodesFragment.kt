package kz.mihael3d.rickandmortycharacters.screens.main.tabs.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentEpisodesBinding
import kz.mihael3d.rickandmortycharacters.presentation.PagingLoadStateAdapter
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesFragment : Fragment(R.layout.fragment_episodes) {
    private lateinit var bindin: FragmentEpisodesBinding

    private val viewModel : EpisodesViewModel by viewModels()
   @Inject
   lateinit var episodesAdapter: EpisodesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindin = FragmentEpisodesBinding.bind(view)

        with(bindin){
            with(episodesAdapter){
                swipeRefresh.setOnRefreshListener { refresh() }
                rvEpisodes.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )
                with(viewModel){
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        episodesFlow.collectLatest { submitData(it) }
                    }
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing =it.refresh is LoadState.Loading
                        }
                    }
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        loadStateFlow.distinctUntilChangedBy {it.refresh}
                            .filter { it.refresh is LoadState.NotLoading }
                            .collect { rvEpisodes.scrollToPosition(0) }
                    }
                }
            }
        }


    }
}