package com.example.radiusagent.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusagent.databinding.FacilitiesSingleItemBinding
import com.example.radiusagent.models.Exclusion
import com.example.radiusagent.models.Facility

class FacilitiesAdapter(
    private val facilitiesList: List<Facility>,
    private val exclusionList: List<List<Exclusion>>
) : RecyclerView.Adapter<FacilitiesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FacilitiesSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            FacilitiesSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return facilitiesList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.tvDialogFacilityIdResult.text = facilitiesList[position].facility_id
        holder.binding.tvDialogFacilityNameResult.text = facilitiesList[position].name
    }
}