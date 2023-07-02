package com.example.radiusagent.fragments

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiusagent.FacilitiesRealm
import com.example.radiusagent.models.Exclusion
import com.example.radiusagent.models.Facilities
import com.example.radiusagent.models.Facility
import com.example.radiusagent.models.Option
import com.example.radiusagent.repository.Repository
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val facilitiesResponse: MutableLiveData<Facilities> = MutableLiveData()
    private lateinit var radiusRealm: Realm

    fun getFacilities() {
        viewModelScope.launch(Dispatchers.IO) {
            facilitiesResponse.postValue(repository.getFacilities())
        }
    }

    var exclusionList: List<List<Exclusion>> = listOf()
    var facility: Facility = Facility(facility_id = "", name = "", options = listOf())
    var option: Option = Option(icon = "", id = "", name = "")


    fun checkExclusion(facilityId: String, optionId: String, exclusions: List<List<Exclusion>>): Boolean {
        for (exclusionList in exclusions) {
            val matchingExclusion = exclusionList.find { exclusion ->
                exclusion.facility_id == facilityId && exclusion.options_id == optionId
            }
            if (matchingExclusion != null) {
                return true // Exclusion found, invalid selection
            }
        }
        return false // No matching exclusion found, valid selection
    }


    fun realmInit(context: Context){
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .name("radiusAgent.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

        radiusRealm = Realm.getDefaultInstance()

    }

    fun addData(facilityName:String){
        val facilities = FacilitiesRealm().apply {
            name = facilityName
        }

        radiusRealm.executeTransaction {
            it.insertOrUpdate(facilities)
        }
    }




}