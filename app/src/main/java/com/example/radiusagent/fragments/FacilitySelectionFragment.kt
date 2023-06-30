package com.example.radiusagent.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FacilitySelectionBinding
import com.example.radiusagent.fragments.adapters.FacilitiesAdapter
import com.example.radiusagent.models.Facility
import com.example.radiusagent.repository.Repository

class FacilitySelectionFragment : Fragment(R.layout.facility_selection) {
    private lateinit var binding: FacilitySelectionBinding
    private lateinit var facilityAdapter: FacilitiesAdapter
    private lateinit var selectedFacility: Facility
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FacilitySelectionBinding.bind(view)
        onClick()
        apiSetup()
    }


    private fun apiSetup() {
        viewModel.getFacilities()
        viewModel.facilitiesResponse.observe(requireActivity()) { facilities ->
            Log.d("API", "apiSetup:${facilities.facilities} ")
            Log.d("API", "apiSetup:${facilities.exclusions} ")
            viewModel.exclusionList = facilities.exclusions
            facilityAdapter = FacilitiesAdapter(
                facilitiesList = facilities.facilities, context = requireContext()
            ) { facility ->
                selectedFacility = facility
            }
            binding.rvFacilities.adapter = facilityAdapter

        }
    }


    private fun onClick() {
        binding.btnSubmitFacilities.setOnClickListener {
            viewModel.facility = selectedFacility

            Log.d("button", "onClick:${viewModel.facility} ")
            Log.d("button", "onClick selectedFacility:${selectedFacility} ")
            findNavController().navigate(FacilitySelectionFragmentDirections.actionFacilitySelectionToOptionsSelectionFragment())
        }
    }

}