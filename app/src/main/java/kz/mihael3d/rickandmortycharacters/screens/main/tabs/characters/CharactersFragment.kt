package kz.mihael3d.rickandmortycharacters.screens.main.tabs.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentCharactersBinding
import kz.mihael3d.rickandmortycharacters.utils.findTopNavController


class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private lateinit var bindin: FragmentCharactersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindin = FragmentCharactersBinding.bind(view)

        bindin.detailButton.setOnClickListener { onEditProfileButtonPressed() }
    }

    private fun onEditProfileButtonPressed() {
        findTopNavController().navigate(R.id.characterDetailFragment)
    }
}