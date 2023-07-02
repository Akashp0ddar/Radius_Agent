package com.example.radiusagent.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusagent.databinding.ExclusionSingleItemBinding
import com.example.radiusagent.models.realmobjects.ExclusionRealm

class ExclusionAdapter(private val exclusionList: List<ExclusionRealm>) :
    RecyclerView.Adapter<ExclusionAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ExclusionSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ExclusionSingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return exclusionList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.rvExclusionNested.adapter =
//            ExclusionNestedAdapter(nestedExclusionList = exclusionList[position])
        holder.binding.tvExclusionFacilityIdResult.text = exclusionList[position].facility_id
        holder.binding.tvExclusionsOptionsIdResult.text = exclusionList[position].options_id
    }
}