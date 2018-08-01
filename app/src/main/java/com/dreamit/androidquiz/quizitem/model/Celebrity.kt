package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Celebrity : RealmObject() {

    @Expose
    @SerializedName("result")
    var result = ""
    @Expose
    @SerializedName("imageAuthor")
    var imageAuthor = ""
    @Expose
    @SerializedName("imageHeight")
    var imageHeight = ""
    @Expose
    @SerializedName("imageUrl")
    var imageUrl = ""
    @Expose
    @SerializedName("show")
    var show = 0
    @Expose
    @SerializedName("name")
    var name = ""
    @Expose
    @SerializedName("imageTitle")
    var imageTitle = ""
    @Expose
    @SerializedName("imageWidth")
    var imageWidth = ""
    @Expose
    @SerializedName("content")
    var content = ""
    @Expose
    @SerializedName("imageSource")
    var imageSource = ""
}