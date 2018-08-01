package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Result : RealmObject() {

    @Expose
    @SerializedName("city")
    var city: Long = 0
    @Expose
    @SerializedName("end_date")
    var end_date = ""
    @Expose
    @SerializedName("result")
    var result: Double = 0.0
    @Expose
    @SerializedName("resolveTime")
    var resolveTime: Long = 0
}