package kz.mihael3d.rickandmortycharacters.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kz.mihael3d.rickandmortycharacters.R
import kz.mihael3d.rickandmortycharacters.databinding.FragmentSplashBinding
import kz.mihael3d.rickandmortycharacters.presentation.MainActivity


class splashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        renderAnimations()
        launchMainScreen()
    }

     private fun launchMainScreen() {

         lifecycleScope.launch{
             delay(1000)
             val intent = Intent(requireContext(), MainActivity::class.java)
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
             startActivity(intent)
         }
    }

    private fun renderAnimations() {
        binding.loadingIndicator.alpha = 0f
        binding.loadingIndicator.animate()
            .alpha(0.7f)
            .setDuration(1000)
            .start()

        binding.pleaseWaitTextView.alpha = 0f
        binding.pleaseWaitTextView.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(1000)
            .start()

    }
}