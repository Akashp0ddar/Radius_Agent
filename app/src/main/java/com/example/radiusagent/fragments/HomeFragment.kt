package com.example.radiusagent.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FacilitySelectionBinding
import com.example.radiusagent.databinding.FragmentHomeBinding
import com.example.radiusagent.fragments.adapters.FacilitiesAdapter
import com.example.radiusagent.repository.Repository


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding.btnSelectFacility.setOnClickListener {
           findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFacilitySelection())
        }
    }


}