package kz.mihael3d.rickandmortycharacters.presentation.charcterdetail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.mihael3d.rickandmortycharacters.databinding.ItemLocationInCharcterDetailBinding
import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode

class EpisodesAdapter : ListAdapter<Episode, EpisodesAdapter.EpisodesViewHolder>(EpisodesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val binding = ItemLocationInCharcterDetailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EpisodesViewHolder(binding)
    }



    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class EpisodesViewHolder(private val bindig: ItemLocationInCharcterDetailBinding):
        RecyclerView.ViewHolder(bindig.root) {

        fun bind(item: Episode){
            with(bindig){
                textView.text = item.id.toString()
            }
        }

        }

    private class EpisodesDiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean =
            oldItem == newItem
    }


}