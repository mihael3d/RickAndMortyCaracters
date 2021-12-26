package kz.mihael3d.rickandmortycharacters.presentation.charcterdetail.view

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.CharacterDetailsFragmentBinding

import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode
import kz.mihael3d.rickandmortycharacters.presentation.Loading
import kz.mihael3d.rickandmortycharacters.presentation.Ready
import kz.mihael3d.rickandmortycharacters.presentation.SearchState
import kz.mihael3d.rickandmortycharacters.presentation.charcterdetail.viewmodel.CharactersDetailViewModel

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(
R.layout.character_details_fragment
) {

    private val args: CharacterDetailFragmentArgs by navArgs()
//    private lateinit var binding: FragmentCharacterDetailBinding
    private val binding by viewBinding(CharacterDetailsFragmentBinding::bind)

    private val viewModel: CharactersDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            viewModel.setEpisodeId("1,2,3,4,5,6,14")
        }




        sharedElementEnterTransition =
            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val episodesList = listOf<Episode>(Episode(id=1),Episode(id=2),Episode(id=3),Episode(id=4),Episode(id=5)
            ,Episode(id=6),Episode(id=7),Episode(id=8),Episode(id=9),Episode(id=10),Episode(id=11),Episode(id=12)
            ,Episode(id=13),Episode(id=14),Episode(id=15),Episode(id=16),Episode(id=17),Episode(id=18),Episode(id=19)
            ,Episode(id=20),Episode(id=21),Episode(id=22),Episode(id=23),Episode(id=24),Episode(id=25),Episode(id=26)
        )

        val episodesAdaptr = EpisodesAdapter()

        postponeEnterTransition()
        var character = args.character
        viewModel.searchState.observe(viewLifecycleOwner, ::handleLoadingState)

        with(binding){



            motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
                override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    doBounceAnimation(shareLayout.favImage)
                    doBounceAnimation(shareLayout.shareImage)
                    doBounceAnimation(shareLayout.seenImage)
                }
                override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
            })


            rvEpisodes.adapter = episodesAdaptr
            viewModel.episodes.observe(viewLifecycleOwner) {data ->
                val a =1
                episodesAdaptr.submitList(data)
            }



            with(character){
                tvName.text = name
//                tvStatus.text = status.toString()
//                setDrawableToMaterialTextView(tvStatus,status)
                Glide
                    .with(ivAvatar.context)
                    .load(image)
                    .centerInside()
//                    .placeholder(R.drawable.ic_profile)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            startPostponedEnterTransition()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            startPostponedEnterTransition()
                            return false
                        }
                    })
                    .into(ivAvatar)
                ViewCompat.setTransitionName(ivAvatar, "avatar_${id}")
                ViewCompat.setTransitionName(tvName, "name_${id}")

//                (view.parent as? ViewGroup)?.doOnPreDraw {
//                    startPostponedEnterTransition()
//                }
            }
        }
    }

    private fun doBounceAnimation(targetView: View) {
        val animator = ObjectAnimator.ofFloat(targetView, "translationY", 0f, -10f, 0f)
        animator.interpolator = BounceInterpolator()
        animator.duration = 1000
        animator.start()
    }

    private fun handleLoadingState(state:SearchState) {
        when (state) {
            Loading -> {
//                binding.loadingIndicator.visibility =  View.VISIBLE
//                binding.loadingIndicator.show()
                binding.loadingIndicator.alpha = 0.0f
                binding.loadingIndicator.animate()
                    .alpha(1.0f)
                    .setDuration(250)
                    .start()
            }
            Ready ->  {
//                binding.loadingIndicator.hide()
//                binding.loadingIndicator.visibility =  View.GONE
//                binding.loadingIndicator.smoothToHide()
                binding.loadingIndicator.alpha = 1.0f
                binding.loadingIndicator.animate()
                    .alpha(0f)
                    .setDuration(250)
                    .start()

            }
        }
    }

    private fun renderAnimations() {
        binding.loadingIndicator.alpha = 0.9f
        binding.loadingIndicator.animate()
            .alpha(0f)
            .setDuration(500)
            .start()


    }


}