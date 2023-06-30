package com.example.radiusagent.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusagent.databinding.ExclusionNestedSingleItemBinding
import com.example.radiusagent.models.Exclusion

class ExclusionNestedAdapter(private val nestedExclusionList: List<Exclusion>) :
    RecyclerView.Adapter<ExclusionNestedAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ExclusionNestedSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ExclusionNestedSingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return nestedExclusionList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvExclusionFacilityIdResult.text = nestedExclusionList[position].facility_id
        holder.binding.tvExclusionsOptionsIdResult.text = nestedExclusionList[position].options_id
    }
}