package kz.mihael3d.rickandmortycharacters.presentation.episodedetail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterImageBinding
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterImageForGridBinding

import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character

class CharactersOfDetailEpisodeListAdapter(
    stateRestorationPolicy: StateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
) :
    ListAdapter<Character, CharacterOfDetailEpisodeViewHolder>(EpisodesDiffCallback()) {

    init {
        this.stateRestorationPolicy = stateRestorationPolicy
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterOfDetailEpisodeViewHolder {
        val binding = ItemCharacterImageForGridBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterOfDetailEpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterOfDetailEpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class EpisodesDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
         oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem == newItem

    }


}