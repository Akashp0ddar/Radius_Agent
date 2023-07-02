package com.example.radiusagent.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FragmentOptionsSelectionBinding
import com.example.radiusagent.fragments.adapters.OptionsAdapter
import com.example.radiusagent.models.realmobjects.OptionRealm
import com.example.radiusagent.repository.Repository

class OptionsSelectionFragment : Fragment(R.layout.fragment_options_selection) {
    private lateinit var binding: FragmentOptionsSelectionBinding
    private lateinit var optionsAdapter: OptionsAdapter
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOptionsSelectionBinding.bind(view)
        initSetup()
        textSetup()
        adapterSetup()
        onClick()
    }

    private fun initSetup() {
        viewModel.option = OptionRealm().apply {
            icon = ""
            id = ""
            name = ""
        }
    }


    private fun textSetup() {
        binding.tvFacilityIdResult.text = viewModel.facility.facility_id
        binding.tvFacilityNameResult.text = viewModel.facility.name
    }


    private fun adapterSetup() {
        optionsAdapter = OptionsAdapter(
            optionsList = viewModel.facility.options!!,
            context = requireContext()
        ) { option ->
            viewModel.option = option
        }
        binding.rvOptions.adapter = optionsAdapter
    }

    private fun onClick() {
        binding.btnSubmitOptions.setOnClickListener {
            if (viewModel.option.id.isNullOrEmpty() &&
                viewModel.option.name.isNullOrEmpty() &&
                viewModel.option.icon.isNullOrEmpty()
            ) {
                Toast.makeText(requireContext(), "Please select one option", Toast.LENGTH_SHORT)
                    .show()
            } else {
                findNavController().navigate(OptionsSelectionFragmentDirections.actionOptionsSelectionFragmentToExclusionFragment())
            }
        }
    }
}