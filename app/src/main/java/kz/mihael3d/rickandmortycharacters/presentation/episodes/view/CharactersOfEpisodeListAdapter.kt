package kz.mihael3d.rickandmortycharacters.presentation.episodes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import kz.mihael3d.rickandmortycharacters.databinding.ItemCharacterImageBinding

import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character

class CharactersOfEpisodeListAdapter(
    stateRestorationPolicy: StateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
) :
    ListAdapter<Character, CharacterOfEpisodeViewHolder>(EpisodesDiffCallback()) {

    init {
        this.stateRestorationPolicy = stateRestorationPolicy
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterOfEpisodeViewHolder {
        val binding = ItemCharacterImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterOfEpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterOfEpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class EpisodesDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
         oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem == newItem

    }


}