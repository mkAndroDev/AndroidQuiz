package com.dreamit.androidquiz.quizlist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Long = 0
    @Expose
    @SerializedName("name")
    var name: String = ""

}