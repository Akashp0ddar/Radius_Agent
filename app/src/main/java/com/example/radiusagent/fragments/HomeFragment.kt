package com.example.radiusagent.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FragmentHomeBinding
import com.example.radiusagent.repository.Repository


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory(Repository()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        apiSetup()
    }

    private fun apiSetup() {
        viewModel.getFacilities()
        viewModel.facilitiesResponse.observe(requireActivity()){facilities->
            Log.d("API", "apiSetup:${facilities.facilities} ")
            Log.d("API", "apiSetup:${facilities.exclusions} ")
        }
    }
}