package com.example.radiusagent.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FacilitySelectionBinding
import com.example.radiusagent.fragments.adapters.FacilitiesAdapter
import com.example.radiusagent.models.realmobjects.FacilityRealm
import com.example.radiusagent.repository.Repository

class FacilitySelectionFragment : Fragment(R.layout.facility_selection) {
    private lateinit var binding: FacilitySelectionBinding
    private lateinit var facilityAdapter: FacilitiesAdapter
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FacilitySelectionBinding.bind(view)
        initViews()
        onClick()
        apiSetup()
    }

    private fun initViews() {
        viewModel.facility = FacilityRealm().apply {
            facility_id = ""
            name = ""
            options = null
        }
    }


    private fun apiSetup() {
        viewModel.getFacilities()
        viewModel.facilitiesResponse.observe(requireActivity()) { facilities ->
            Log.d("API", "apiSetup:${facilities.facilities} ")
            Log.d("API", "apiSetup:${facilities.exclusions} ")
            if (facilities.facilities.isNotEmpty() && facilities.exclusions.isNotEmpty()) {
                viewModel.saveDataOffline(facilitiesFromApi = facilities)
                viewModel.exclusionList = facilities.exclusions
                facilityAdapter = FacilitiesAdapter(
                    facilitiesList = viewModel.facilityRealmList(), context = requireContext()
                ) { facility ->
                    viewModel.facility = facility
                }
                binding.rvFacilities.adapter = facilityAdapter
            }

        }
    }


    private fun onClick() {
        binding.btnSubmitFacilities.setOnClickListener {
            if (viewModel.facility.facility_id.isNullOrEmpty() && viewModel.facility.name.isNullOrEmpty() && viewModel.facility.options.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please select one facility", Toast.LENGTH_SHORT)
                    .show()
            } else {
                findNavController().navigate(FacilitySelectionFragmentDirections.actionFacilitySelectionToOptionsSelectionFragment())
            }
        }
    }

}