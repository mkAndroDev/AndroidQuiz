package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Rate : RealmObject() {

    @Expose
    @SerializedName("from")
    var from = 0
    @Expose
    @SerializedName("to")
    var to = 0
    @Expose
    @SerializedName("content")
    var content = ""
}