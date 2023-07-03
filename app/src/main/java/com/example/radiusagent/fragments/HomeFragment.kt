package com.example.radiusagent.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.radiusagent.R
import com.example.radiusagent.ViewModelFactory
import com.example.radiusagent.databinding.FragmentHomeBinding
import com.example.radiusagent.repository.Repository
import com.example.radiusagent.utils.AppUtils
import com.example.radiusagent.utils.Constants


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by activityViewModels<HomeViewModel> { ViewModelFactory(Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initViews()
        setUpViews()
        onClick()
    }

    private fun initViews() {
        viewModel.realmInit(context = requireContext())
        AppUtils.initializePreferences(context = requireContext())
        viewModel.networkObservation(context = requireContext())

    }

    private fun setUpViews() {
        binding.tvFacilityIdResult.text = viewModel.facility.facility_id
        binding.tvFacilityNameResult.text = viewModel.facility.name
        binding.tvOptionsIdResult.text = viewModel.option.id
        binding.tvOptionsNameResult.text = viewModel.option.name
        if (!viewModel.option.icon.isNullOrEmpty()) {
            binding.ivIcon.isVisible = true
            iconSetup(viewModel.option.icon!!)
        }
    }

    private fun onClick() {
        binding.btnSelectFacility.setOnClickListener {

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFacilitySelection())
        }
    }


    private fun iconSetup(icon: String) {
        when (icon) {
            Constants.APARTMENT -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.apartment
                    )
                )
            }

            Constants.CONDO -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.condo
                    )
                )
            }

            Constants.BOAT -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.boat
                    )
                )
            }


            Constants.LAND -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.land
                    )
                )
            }

            Constants.ROOMS -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.rooms
                    )
                )
            }

            Constants.NO_ROOM -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.no_room
                    )
                )
            }

            Constants.SWIMMING -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.swimming
                    )
                )
            }

            Constants.GARDEN -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.garden
                    )
                )
            }


            Constants.GARAGE -> {
                binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.garage
                    )
                )
            }
        }
    }


}