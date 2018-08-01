package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class SponsoredResults : RealmObject() {

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
    @SerializedName("imageWidth")
    var imageWidth = ""
    @Expose
    @SerializedName("textColor")
    var textColor = ""
    @Expose
    @SerializedName("content")
    var content = ""
    @Expose
    @SerializedName("mainColor")
    var mainColor = ""
    @Expose
    @SerializedName("imageSource")
    var imageSource = ""

}