package kz.mihael3d.rickandmortycharacters.presentation.episodedetail.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
//import com.bumptech.glide.manager.Lifecycle
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentEpisodeDetailBinding
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.presentation.character.viewmodel.CharactersResult
import kz.mihael3d.rickandmortycharacters.presentation.charcterdetail.view.CharacterDetailFragmentArgs
import kz.mihael3d.rickandmortycharacters.presentation.episodedetail.viewmodel.EpisodeDetailViewModel
import kz.mihael3d.rickandmortycharacters.presentation.episodes.view.CharactersOfEpisodeListAdapter
import kz.mihael3d.rickandmortycharacters.utils.SpannedGridLayoutManager

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment(
    R.layout.fragment_episode_detail
)
{
    private val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    private val viewModel: EpisodeDetailViewModel by viewModels()

    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            val characterList = args.episode.characters
            viewModel.setCharactersList(characterList)
        }
//        sharedElementEnterTransition =
//            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var  episode = args.episode
        binding.cardView.transitionName = "${episode.id}"

        prepareSharedElementTransition(view)
        postponeEnterTransition()

        view.doOnNextLayout {
            (it.parent as? ViewGroup)?.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }

        setUpHorizontalImageList()

    }

    private fun prepareSharedElementTransition(view: View) {

        sharedElementEnterTransition = MaterialContainerTransform()
            .apply {
                duration = 500
                // Scope the transition to a view in the hierarchy so we know it will be added under
                // the bottom app bar but over the elevation scale of the exiting HomeFragment.
                drawingViewId = R.id.tabs_container
                scrimColor = Color.TRANSPARENT
//                setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
            }
    }

    private fun setUpHorizontalImageList(){
        binding.charactersList.apply{
            layoutManager = SpannedGridLayoutManager(3, 1f)
            val listAdapter = CharactersOfDetailEpisodeListAdapter()
            this.adapter =  listAdapter
            setHasFixedSize(true)

            lifecycleScope.launch{
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.characters.collect { characters ->
                        when (characters) {
                            is CharactersResult.ValidResult ->    listAdapter.submitList(characters.result)
                        }
                    }
                }
            }

//            val episodesList = listOf<Character>(
//                Character(id=0, image= "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
//                Character(id=1, image= "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
//                Character(id=2, image= "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
//                Character(id=3, image= "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
//                Character(id=4, image= "https://rickandmortyapi.com/api/character/avatar/5.jpeg"),
//                Character(id=5, image= "https://rickandmortyapi.com/api/character/avatar/6.jpeg"),
//                Character(id=6, image= "https://rickandmortyapi.com/api/character/avatar/7.jpeg"),
//                Character(id=7, image= "https://rickandmortyapi.com/api/character/avatar/8.jpeg")
//
//            )


//            listAdapter.submitList(episodesList)

        }



    }
}