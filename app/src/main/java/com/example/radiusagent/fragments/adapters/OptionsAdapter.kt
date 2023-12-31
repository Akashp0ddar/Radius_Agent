package com.example.radiusagent.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusagent.R
import com.example.radiusagent.databinding.OptionSelectionSingleItemBinding
import com.example.radiusagent.models.realmobjects.OptionRealm
import com.example.radiusagent.utils.Constants

class OptionsAdapter(
    private val optionsList: List<OptionRealm>,
    private val context: Context,
    private val onItemClick: (option: OptionRealm) -> Unit
) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {
    private var selectionItemPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(val binding: OptionSelectionSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            OptionSelectionSingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvDialogOptionsIdResult.text = optionsList[position].id
        holder.binding.tvDialogFacilityNameResult.text = optionsList[position].name
        iconSetup(icon = optionsList[position].icon!!, optionsViewHolder = holder)
        if (position == selectionItemPosition) {
            holder.binding.clOptionSelections.background =
                AppCompatResources.getDrawable(context, R.drawable.item_select_bg)
        } else {
            holder.binding.clOptionSelections.background =
                AppCompatResources.getDrawable(context, R.drawable.bg_cl_home_screen)

        }
        holder.itemView.setOnClickListener {
            val previousSelectedItem = selectionItemPosition
            selectionItemPosition = holder.adapterPosition
            notifyItemChanged(previousSelectedItem)
            notifyItemChanged(selectionItemPosition)
            onItemClick(optionsList[position])
        }
    }


    override fun getItemCount(): Int {
        return optionsList.size

    }


    private fun iconSetup(icon: String, optionsViewHolder: ViewHolder) {
        when (icon) {
            Constants.APARTMENT -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.apartment
                    )
                )
            }

            Constants.CONDO -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.condo
                    )
                )
            }

            Constants.BOAT -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.boat
                    )
                )
            }


            Constants.LAND -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.land
                    )
                )
            }

            Constants.ROOMS -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.rooms
                    )
                )
            }

            Constants.NO_ROOM -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.no_room
                    )
                )
            }

            Constants.SWIMMING -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.swimming
                    )
                )
            }

            Constants.GARDEN -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.garden
                    )
                )
            }


            Constants.GARAGE -> {
                optionsViewHolder.binding.ivIcon.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.garage
                    )
                )
            }

        }

    }


}