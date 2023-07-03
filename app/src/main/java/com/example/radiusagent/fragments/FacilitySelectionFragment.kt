package com.example.radiusagent.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FacilitySelectionBinding
import com.example.radiusagent.fragments.adapters.FacilitiesAdapter
import com.example.radiusagent.models.realmobjects.FacilityRealm
import com.example.radiusagent.models.realmobjects.OptionRealm
import com.example.radiusagent.repository.Repository
import com.example.radiusagent.utils.AppUtils
import com.example.radiusagent.utils.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FacilitySelectionFragment : Fragment(R.layout.facility_selection) {
    private lateinit var binding: FacilitySelectionBinding
    private lateinit var facilityAdapter: FacilitiesAdapter
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FacilitySelectionBinding.bind(view)
        initViews()
        onClick()
        showData()
    }


    private fun initViews() {
        apiRefresh(AppUtils.getSavedStringFromPreferences(Constants.DATE_TO_COMPARE))
        viewModel.facility = FacilityRealm().apply {
            facility_id = ""
            name = ""
            options = null
        }

        viewModel.option = OptionRealm().apply {
            icon = ""
            id = ""
            name = ""
        }
    }


    private fun apiRefresh(savedDate: String?): Boolean {
        if (savedDate == null) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            AppUtils.saveStringInPreferences(
                key = Constants.DATE_TO_COMPARE,
                value = dateFormat.format(Date())
            )
            return false
        }
        // Get the current date
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time

        // Format the current date as a string
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDateStr = dateFormat.format(currentDate)

        // Compare the dates
        val comparisonResult = currentDateStr.compareTo(savedDate)

        if (comparisonResult > 0) {
            AppUtils.saveStringInPreferences(
                key = Constants.DATE_TO_COMPARE,
                value = currentDateStr
            )
            binding.btnSubmitUpdate.isVisible = true
        }

        return comparisonResult > 0
    }


    private fun showData() {
        if (viewModel.activeNetworkStatus.value == true) {
            apiSetup()
        } else {
            if (viewModel.facilityRealmList().isNotEmpty()){
                showDataFromDataBase()
            }else{
                Toast.makeText(requireContext(), requireActivity().getString(R.string.turn_on_internet), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun apiSetup() {
        viewModel.clearRealm()
        viewModel.getFacilities()
        viewModel.facilitiesResponse.observe(requireActivity()) { facilities ->
            if (facilities.facilities.isNotEmpty() && facilities.exclusions.isNotEmpty()) {
                viewModel.saveDataOffline(facilitiesFromApi = facilities)

                binding.btnSubmitUpdate.isVisible = false
                facilityAdapter = FacilitiesAdapter(
                    facilitiesList = viewModel.facilityRealmList(), context = requireContext()
                ) { facility ->
                    viewModel.facility = facility
                }
                binding.rvFacilities.adapter = facilityAdapter
            }

        }
    }

    private fun showDataFromDataBase() {
        facilityAdapter = FacilitiesAdapter(
            facilitiesList = viewModel.facilityRealmList(), context = requireContext()
        ) { facility ->
            viewModel.facility = facility
        }
        binding.rvFacilities.adapter = facilityAdapter
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

        binding.btnSubmitUpdate.setOnClickListener {
            if (viewModel.activeNetworkStatus.value == true) {

                apiSetup()
            } else {
                Toast.makeText(requireContext(), "Please turn on your internet", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}