package kz.mihael3d.rickandmortycharacters.screens.main.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentTabsBinding


class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            binding.bottomNavigationView.isVisible = arguments?.getBoolean("ShowAppBar", false) == true
        }
    }

}