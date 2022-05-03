package kz.mihael3d.rickandmortycharacters.presentation.episodes.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.ItemEpisodeBinding
import kz.mihael3d.rickandmortycharacters.domain.characters.models.Character
import kz.mihael3d.rickandmortycharacters.domain.episodes.models.Episode
//import kz.mihael3d.rickandmortycharacters.domain.episodes.models.EpisodeWithCharacters
import kz.mihael3d.rickandmortycharacters.utils.doAsync

class EpisodeViewHolder(
    private val binding: ItemEpisodeBinding,
    private val onItemClick: ((ItemEpisodeBinding, Episode) -> Unit)? = null,
    private val loadCharacters: (suspend (List<String>) -> List<Character>)
) : RecyclerView.ViewHolder(binding.root) {

    var isExpanded = false

    fun bind(item: Episode, expandedIds: MutableSet<Int>) {

        isExpanded = expandedIds.contains(item.id)

        binding.tvCode.text = item.code
        binding.tvName.text = item.name
//        binding.charactersList.doAsync(
//            {
//                loadCharacters(item.characters)
////                 listOf(
////                    Character(id = 1, image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
////                    Character(id = 4, image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
////                    Character(id = 13, image = "https://rickandmortyapi.com/api/character/avatar/13.jpeg")
////                )
//            }
//
//        ) {
//            setUpHorizontalImageList(it!!)}

        binding.cardView.transitionName ="${item.id}"

        binding.ivExpand.setOnClickListener {

                val parent = (itemView.parent as ViewGroup) ?: return@setOnClickListener

                val transition = TransitionInflater.from(itemView.context)
                    .inflateTransition(R.transition.icon_expand_toggle)
                TransitionManager.beginDelayedTransition(parent, transition)

                isExpanded = !isExpanded

                if(isExpanded) {
                    expandedIds.add(item.id)
                } else {
                    expandedIds.remove(item.id)
                }

                setUpExpandedStatus(item)
            }

        binding.root.setOnClickListener{
            onItemClick?.invoke(binding, item)
        }

        setUpExpandedStatus(item)
    }

   private fun setUpHorizontalImageList(characterList: List<Character>){
       binding.charactersList.apply{
           val listAdapter = CharactersOfEpisodeListAdapter()
           this.adapter =  listAdapter
           setHasFixedSize(true)



           listAdapter.submitList(characterList)

       }
   }

    private fun setUpExpandedStatus(item: Episode) {
        if (isExpanded) {
            binding.charactersList.visibility = View.VISIBLE
            binding.ivExpand.rotationX = 180f
            binding.charactersList.doAsync(
                {
                    loadCharacters(item.characters)
                }
            ) {
                setUpHorizontalImageList(it!!)}

        } else {
            binding.charactersList.visibility = View.GONE
            binding.ivExpand.rotationX = 0f
        }
    }
}