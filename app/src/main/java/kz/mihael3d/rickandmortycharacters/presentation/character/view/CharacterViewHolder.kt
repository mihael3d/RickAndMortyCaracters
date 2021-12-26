package kz.mihael3d.rickandmortycharacters.presentation.character.view

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterBinding
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.utils.setDrawableToMaterialTextView

class CharacterViewHolder(
    private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root)  {



        init {
//            itemView.setOnClickListener{
//                characterClickListener?.onCharacterClickListener(binding,
//                    getItem(absoluteAdapterPosition) as Character
//                )
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