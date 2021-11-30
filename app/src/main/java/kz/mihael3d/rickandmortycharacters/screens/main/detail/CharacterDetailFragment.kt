package kz.mihael3d.rickandmortycharacters.screens.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentCharacterDetailBinding


class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {


    private lateinit var binding: FragmentCharacterDetailBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDetailBinding.bind(view)
    }
}