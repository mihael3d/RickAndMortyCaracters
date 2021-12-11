package kz.mihael3d.rickandmortycharacters.screens.main.tabs.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.mihael3d.rickandmortycharacters.data.model.entites.Episode
import kz.mihael3d.rickandmortycharacters.data.model.entites.Location
import kz.mihael3d.rickandmortycharacters.databinding.ItemEpisodeBinding
import kz.mihael3d.rickandmortycharacters.databinding.ItemLocationBinding

import javax.inject.Inject

class LocationsAdapter @Inject constructor() :
    PagingDataAdapter<Location, LocationsAdapter.LocationViewHolder>(LocationComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocationViewHolder(
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false)
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class  LocationViewHolder(private val bindig: ItemLocationBinding) :
        RecyclerView.ViewHolder(bindig.root) {
        fun bind(item: Location) {
            with(bindig){
                tvName.text = item.name
            }
        }
    }


    object LocationComparator : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location) =
            oldItem.id ==  newItem.id


        override fun areContentsTheSame(oldItem: Location, newItem: Location) =
            oldItem ==  newItem

    }
}