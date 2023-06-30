package com.example.radiusagent

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.databinding.FragmentOptionsSelectionBinding
import com.example.radiusagent.fragments.HomeViewModel
import com.example.radiusagent.fragments.adapters.OptionsAdapter
import com.example.radiusagent.repository.Repository

class OptionsSelectionFragment : Fragment(R.layout.fragment_options_selection) {
    private lateinit var binding: FragmentOptionsSelectionBinding
    private lateinit var optionsAdapter: OptionsAdapter
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOptionsSelectionBinding.bind(view)
        textSetup()
        adapterSetup()
        onClick()
    }


    private fun textSetup() {
        binding.tvFacilityIdResult.text = viewModel.facility.facility_id
        binding.tvFacilityNameResult.text = viewModel.facility.name
    }


    private fun adapterSetup() {
        optionsAdapter = OptionsAdapter(
            optionsList = viewModel.facility.options,
            context = requireContext()
        ) { option ->
            viewModel.option = option
        }
        binding.rvOptions.adapter = optionsAdapter
    }

    private fun onClick() {
        binding.btnSubmitOptions.setOnClickListener {
            if (viewModel.option.id.isEmpty() &&
                viewModel.option.name.isEmpty() &&
                viewModel.option.icon.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Please select one option", Toast.LENGTH_SHORT)
                    .show()
            } else {

                findNavController().navigate(OptionsSelectionFragmentDirections.actionOptionsSelectionFragmentToHomeFragment())
            }
        }
    }
}