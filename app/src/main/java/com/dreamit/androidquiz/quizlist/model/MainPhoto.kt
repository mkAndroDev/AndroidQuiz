package com.dreamit.androidquiz.quizlist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MainPhoto : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("media_id")
    var id: Long = 0
    @Expose
    @SerializedName("author")
    var author: String = ""
    @Expose
    @SerializedName("width")
    var width: String = ""
    @Expose
    @SerializedName("source")
    var source: String = ""
    @Expose
    @SerializedName("title")
    var title: String = ""
    @Expose
    @SerializedName("url")
    var url: String = ""
    @Expose
    @SerializedName("height")
    var height: String = ""

}