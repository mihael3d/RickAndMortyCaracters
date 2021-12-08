package kz.mihael3d.rickandmortycharacters.screens.main.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentCharacterDetailBinding
import kz.mihael3d.rickandmortycharacters.utils.setDrawableToMaterialTextView


class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDetailBinding.bind(view)
        postponeEnterTransition()
        var character = args.character
        with(binding){
            with(character){
                tvName.text = name
                tvStatus.text = status.toString()
                setDrawableToMaterialTextView(tvStatus,status)
                Glide
                    .with(ivAvatar.context)
                    .load(image)
                    .centerInside()
//                    .centerCrop()
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
}