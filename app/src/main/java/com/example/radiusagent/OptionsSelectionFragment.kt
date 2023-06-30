package com.example.radiusagent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.radiusagent.databinding.FragmentOptionsSelectionBinding
import com.example.radiusagent.fragments.HomeViewModel
import com.example.radiusagent.fragments.adapters.OptionsAdapter
import com.example.radiusagent.repository.Repository

class OptionsSelectionFragment : Fragment(R.layout.fragment_options_selection) {
    private lateinit var binding:FragmentOptionsSelectionBinding
    private lateinit var optionsAdapter: OptionsAdapter
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOptionsSelectionBinding.bind(view)
        textSetup()
        adapterSetup()
    }

    private fun textSetup() {
        binding.tvFacilityIdResult.text = viewModel.facility.facility_id
        binding.tvFacilityNameResult.text = viewModel.facility.name
    }


    private fun adapterSetup() {
        optionsAdapter = OptionsAdapter(optionsList = viewModel.facility.options, context = requireContext())
        binding.rvOptions.adapter = optionsAdapter
    }
}