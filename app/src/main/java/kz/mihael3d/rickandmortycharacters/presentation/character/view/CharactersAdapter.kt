package kz.mihael3d.rickandmortycharacters.presentation.character.view

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterBinding
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character

class CharactersAdapter() : ListAdapter<Character, CharacterViewHolder>(CharacterComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    private class CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

}