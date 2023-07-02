package com.example.radiusagent.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiusagent.models.Exclusion
import com.example.radiusagent.models.Facilities
import com.example.radiusagent.models.realmobjects.ExclusionRealm
import com.example.radiusagent.models.realmobjects.ExclusionsRealm
import com.example.radiusagent.models.realmobjects.FacilityRealm
import com.example.radiusagent.models.realmobjects.OptionRealm
import com.example.radiusagent.repository.Repository
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.kotlin.where
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
    var facility: FacilityRealm = FacilityRealm().apply {
        facility_id = ""
        name = ""
        options = null
    }

    var option: OptionRealm = OptionRealm().apply {
        icon = ""
        id = ""
        name = ""
    }


    fun realmInit(context: Context) {
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

    fun saveDataOffline(facilitiesFromApi: Facilities) {

        val facilityRealmList = RealmList<FacilityRealm>()
        val exclusionsRealm = ExclusionsRealm()


        facilitiesFromApi.facilities.forEach { facility ->
            val facilityRealm = FacilityRealm()
            facilityRealm.facility_id = facility.facility_id
            facilityRealm.name = facility.name
            val optionRealmList = RealmList<OptionRealm>()
            facility.options.forEach { facilityOptionsFromApi ->
                val optionRealm = OptionRealm()
                optionRealm.id = facilityOptionsFromApi.id
                optionRealm.name = facilityOptionsFromApi.name
                optionRealm.icon = facilityOptionsFromApi.icon
                optionRealmList.add(optionRealm)
            }
            facilityRealm.options = optionRealmList

            facilityRealmList.add(facilityRealm)
        }


        facilitiesFromApi.exclusions.forEach { exclusionsFromApi ->
            val exclusionRealm = RealmList<ExclusionRealm>()
            exclusionsFromApi.forEach { exclusion ->
                val exclusionRealmNested = ExclusionRealm().apply {
                    facility_id = exclusion.facility_id
                    options_id = exclusion.options_id
                }
                exclusionRealm.add(exclusionRealmNested)
            }
            exclusionsRealm.exclusionRealm.addAll(exclusionRealm)
        }


        radiusRealm.executeTransaction {
            it.insertOrUpdate(facilityRealmList)
            it.insertOrUpdate(exclusionsRealm)
        }
    }


    fun facilityRealmList(): List<FacilityRealm> {
        return radiusRealm.where<FacilityRealm>().findAll()
    }

    fun exclusionRealmList(): List<ExclusionRealm> {
        return radiusRealm.where<ExclusionRealm>().findAll()
    }


    fun checkExclusion(
        exclusions: List<ExclusionRealm>,
        facilityId: String,
        optionId: String
    ): Boolean {
        exclusions.forEach { exclusion ->
            if (exclusion.facility_id == facilityId && exclusion.options_id == optionId) {
                return true
            }
        }
        return false
    }


    private var activeNetworkStatusMLD = MutableLiveData<Boolean>()
    val activeNetworkStatus: LiveData<Boolean>
        get() = activeNetworkStatusMLD


    fun networkObservation(context: Context) {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d("Network", "onAvailable: $network")
                activeNetworkStatusMLD.postValue(true)
            }


            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val unmetered =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d("Network", "onAvailable: $network")
                activeNetworkStatusMLD.postValue(false)
            }
        }

        val connectivityManager = ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        ) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }


}