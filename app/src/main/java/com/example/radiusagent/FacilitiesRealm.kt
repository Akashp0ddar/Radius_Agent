package com.example.radiusagent

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField
import org.bson.types.ObjectId

open class FacilitiesRealm : RealmObject() {

    @PrimaryKey
    @RealmField("_id")
    var id: ObjectId = ObjectId()
    var name: String = ""
}