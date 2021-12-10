package kz.mihael3d.rickandmortycharacters.screens.main.tabs.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.databinding.ItemEpisodeBinding
import javax.inject.Inject

class EpisodesAdapter @Inject constructor() :
    PagingDataAdapter<Episode, EpisodesAdapter.EpisodeViewHolder>(EpisodeComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context)
        ,parent,false)
    )

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
    inner class EpisodeViewHolder(private val bindig: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(bindig.root) {
            fun bind(item: Episode) {
                with(bindig){
                    tvCode.text = item.code
                    tvName.text = item.name
                }
            }
        }


    object EpisodeComparator : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem.id ==  newItem.id


        override fun areContentsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem ==  newItem

    }
}