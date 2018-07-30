package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Image : RealmObject() {

    @Expose
    @SerializedName("image")
    var image = ""
    @Expose
    @SerializedName("author")
    var author = ""
    @Expose
    @SerializedName("width")
    var width = ""
    @Expose
    @SerializedName("mediaId")
    var mediaId = ""
    @Expose
    @SerializedName("source")
    var source = ""
    @Expose
    @SerializedName("url")
    var url = ""
    @Expose
    @SerializedName("height")
    var height = ""
}