package com.example.radiusagent.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusagent.R
import com.example.radiusagent.databinding.FacilitiesSingleItemBinding
import com.example.radiusagent.models.realmobjects.FacilityRealm

class FacilitiesAdapter(
    private val facilitiesList: List<FacilityRealm>,
    private val context: Context,
    private val onItemClick: (facility: FacilityRealm) -> Unit
) : RecyclerView.Adapter<FacilitiesAdapter.ViewHolder>() {
    private var selectionItemPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(val binding: FacilitiesSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            FacilitiesSingleItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return facilitiesList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvDialogFacilityIdResult.text = facilitiesList[position].facility_id
        holder.binding.tvDialogFacilityNameResult.text = facilitiesList[position].name

        if (position == selectionItemPosition) {
            holder.binding.clFacilitiesSingleItem.background =
                AppCompatResources.getDrawable(context, R.drawable.item_select_bg)
        } else {
            holder.binding.clFacilitiesSingleItem.background =
                AppCompatResources.getDrawable(context, R.drawable.bg_cl_home_screen)

        }
        holder.itemView.setOnClickListener {
            val previousSelectedItem = selectionItemPosition
            selectionItemPosition = holder.adapterPosition
            notifyItemChanged(previousSelectedItem)
            notifyItemChanged(selectionItemPosition)

            onItemClick(facilitiesList[position])
        }

    }
}