package com.example.radiusagent.fragments

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FacilitySelectionDialogBinding
import com.example.radiusagent.databinding.FragmentHomeBinding
import com.example.radiusagent.fragments.adapters.FacilitiesAdapter
import com.example.radiusagent.repository.Repository


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var facilityAdapter: FacilitiesAdapter
    private lateinit var dialog: AlertDialog
    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory(Repository()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        apiSetup()
        onClick()
    }

    private fun onClick() {
        binding.btnSelectFacility.setOnClickListener {
            if (::facilityAdapter.isInitialized){
                facilitySelectionDialog(context = requireContext())
            }
        }
    }

    private fun facilitySelectionDialog(context: Context) {

        val facilityDialogBinding =
            FacilitySelectionDialogBinding.inflate(LayoutInflater.from(context))

        facilityDialogBinding.rvFacilities.adapter = facilityAdapter

        dialog = AlertDialog.Builder(context)
            .setView(facilityDialogBinding.root)
            .setTitle(context.getString(R.string.facilities))
            .create()

        dialog.show()

        facilityDialogBinding.btnSubmitFacilities.setOnClickListener {
            dialog.dismiss()
        }


    }

    private fun apiSetup() {
        viewModel.getFacilities()
        viewModel.facilitiesResponse.observe(requireActivity()) { facilities ->
            Log.d("API", "apiSetup:${facilities.facilities} ")
            Log.d("API", "apiSetup:${facilities.exclusions} ")
            facilityAdapter = FacilitiesAdapter(
                facilitiesList = facilities.facilities,
                exclusionList = facilities.exclusions
            )
        }
    }
}