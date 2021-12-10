package kz.mihael3d.rickandmortycharacters.screens.main.tabs.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.data.model.Character
import kz.mihael3d.rickandmortycharacters.data.model.Status
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterBinding
import javax.inject.Inject

import com.bumptech.glide.Glide
import kz.mihael3d.rickandmortycharacters.utils.setDrawableToMaterialTextView


class CharactersAdapter @Inject constructor() :
    PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharacterComparator) {
    var characterClickListener: CharacterClickListener? = null


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
       return CharacterViewHolder(
           binding = ItemCharacterBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener{
                characterClickListener?.onCharacterClickListener(binding,
                    getItem(absoluteAdapterPosition) as Character)
            }
        }
            fun bind(item: Character) {
                with(binding){
                    tvName.text = item.name
                    Glide
                        .with(ivAvatar.context)
                        .load(item.image)
                        .centerInside()
                        .into(ivAvatar)
                    tvStatus.text = item.status.toString()

                    setDrawableToMaterialTextView(tvStatus, item.status)
                    ViewCompat.setTransitionName(ivAvatar, "avatar_${item.id}")
                    ViewCompat.setTransitionName(tvName, "name_${item.id}")

                }
            }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

    interface CharacterClickListener {
        fun onCharacterClickListener(binding: ItemCharacterBinding, character: Character)
    }
}