package com.example.radiusagent.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiusagent.models.Exclusion
import com.example.radiusagent.models.Facilities
import com.example.radiusagent.models.Facility
import com.example.radiusagent.models.Option
import com.example.radiusagent.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val facilitiesResponse: MutableLiveData<Facilities> = MutableLiveData()

    fun getFacilities() {
        viewModelScope.launch(Dispatchers.IO) {
            facilitiesResponse.postValue(repository.getFacilities())
        }
    }

    var exclusionList: List<List<Exclusion>> = listOf()
    var facility: Facility = Facility(facility_id = "", name = "", options = listOf())
    var option: Option = Option(icon = "", id = "", name = "")

}