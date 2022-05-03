package kz.mihael3d.rickandmortycharacters.presentation.episodedetail.view


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterImageBinding
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterImageForGridBinding
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character

class CharacterOfDetailEpisodeViewHolder (
    private val binding: ItemCharacterImageForGridBinding
    ) :RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: Character) {
        with(binding){
           Glide
                .with(ivAvatar.context)
                .load(item.image)
                .centerInside()
                .into(ivAvatar)

        }
    }
}