package com.example.radiusagent.models.realmobjects

import io.realm.RealmList
import io.realm.RealmObject

open class FacilitiesRealm : RealmObject() {
    var exclusions: RealmList<ExclusionsRealm>? = RealmList<ExclusionsRealm>()
    var facilities: RealmList<FacilityRealm>? = null
}

//@RealmClass(embedded = true)
open class FacilityRealm : RealmObject() {
    var facility_id: String? = null
    var name: String? = null
    var options: RealmList<OptionRealm>? = null
}

//@RealmClass(embedded = true)
open class OptionRealm : RealmObject() {
    var icon: String? = null
    var id: String? = null
    var name: String? = null
}

//@RealmClass(embedded = true)
open class ExclusionsRealm : RealmObject() {
    var exclusionRealm: RealmList<ExclusionRealm> = RealmList<ExclusionRealm>()
}

//@RealmClass(embedded = true)
open class ExclusionRealm : RealmObject() {
    var facility_id: String? = null
    var options_id: String? = null
}
