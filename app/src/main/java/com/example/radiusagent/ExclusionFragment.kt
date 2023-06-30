package com.example.radiusagent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.databinding.FragmentExclusionBinding
import com.example.radiusagent.fragments.HomeViewModel
import com.example.radiusagent.fragments.adapters.ExclusionAdapter
import com.example.radiusagent.repository.Repository
import com.example.radiusagent.utils.Constants


class ExclusionFragment : Fragment(R.layout.fragment_exclusion) {
    private lateinit var binding:FragmentExclusionBinding
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }
    private lateinit var exclusionAdapter: ExclusionAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExclusionBinding.bind(view)
        adapterSetUp()
        setUpPreview()
        onClick()
    }

    private fun setUpPreview() {
        binding.tvDialogFacilityIdResult.text = viewModel.facility.facility_id
        binding.tvDialogFacilityNameResult.text = viewModel.facility.name
        binding.tvOptionsIdResult.text = viewModel.option.id
        binding.tvOptionsNameResult.text = viewModel.option.name
        iconSetup(viewModel.option.icon)

    }

    private fun onClick() {
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(ExclusionFragmentDirections.actionExclusionFragmentToHomeFragment())
        }
    }

    private fun adapterSetUp() {
        exclusionAdapter = ExclusionAdapter(exclusionList = viewModel.exclusionList)
        binding.rvExclusion.adapter = exclusionAdapter

    }


    private fun iconSetup(icon: String) {
        when (icon) {
            Constants.APARTMENT -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.apartment
                    )
                )
            }

            Constants.CONDO -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.condo
                    )
                )
            }

            Constants.BOAT -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.boat
                    )
                )
            }


            Constants.LAND -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.land
                    )
                )
            }

            Constants.ROOMS -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.rooms
                    )
                )
            }

            Constants.NO_ROOM -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.no_room
                    )
                )
            }

            Constants.SWIMMING -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.swimming
                    )
                )
            }

            Constants.GARDEN -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.garden
                    )
                )
            }


            Constants.GARAGE -> {
                binding.ivIconExclusion.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.garage
                    )
                )
            }
        }
    }

}