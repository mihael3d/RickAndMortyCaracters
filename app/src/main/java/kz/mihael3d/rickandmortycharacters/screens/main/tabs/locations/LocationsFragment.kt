package kz.mihael3d.rickandmortycharacters.screens.main.tabs.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentLocationsBinding
import javax.inject.Inject

@AndroidEntryPoint
class LocationsFragment : Fragment(R.layout.fragment_locations) {
 private lateinit var bindin: FragmentLocationsBinding

    private val viewModel : LocationsViewModel by viewModels()
    @Inject
    lateinit var locationsAdapter: LocationsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindin = FragmentLocationsBinding.bind(view)

        with(bindin){
            with(locationsAdapter){
                swipeRefresh.setOnRefreshListener { refresh() }
                rvLocations.adapter = this

//                    withLoadStateHeaderAndFooter(
//                    header = PagingLoadStateAdapter(this),
//                    footer = PagingLoadStateAdapter(this)
//                )
                with(viewModel){
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        locationsFlow.collectLatest { submitData(it) }
                    }
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing =it.refresh is LoadState.Loading
                        }
                    }
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated{
                        loadStateFlow.distinctUntilChangedBy {it.refresh}
                            .filter { it.refresh is LoadState.NotLoading }
                            .collect { rvLocations.scrollToPosition(0) }
                    }
                }
            }
        }


    }
}