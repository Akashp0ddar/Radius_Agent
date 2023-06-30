package com.example.radiusagent.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusagent.databinding.ExclusionListSingleItemBinding
import com.example.radiusagent.models.Exclusion

class ExclusionAdapter(private val exclusionList: List<List<Exclusion>>) :
    RecyclerView.Adapter<ExclusionAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ExclusionListSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ExclusionListSingleItemBinding.inflate(
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
        holder.binding.rvExclusionNested.adapter =
            ExclusionNestedAdapter(nestedExclusionList = exclusionList[position])
    }
}