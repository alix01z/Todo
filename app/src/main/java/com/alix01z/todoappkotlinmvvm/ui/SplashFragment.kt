package com.alix01z.todoappkotlinmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private val SPLASH_DURATION = 3500L
    private lateinit var binding : FragmentSplashBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_splash , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            delay(2000)
            navigateToHomeFragment()

        }
    }
    private fun navigateToHomeFragment(){
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

    }
}