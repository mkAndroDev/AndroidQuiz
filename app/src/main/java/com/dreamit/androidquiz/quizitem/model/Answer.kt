package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Answer : RealmObject() {

    @Expose
    @SerializedName("image")
    var image: Image? = null
    @Expose
    @SerializedName("order")
    var order = 0
    @Expose
    @SerializedName("text")
    var text = ""
    @Expose
    @SerializedName("isCorrect")
    var isCorrect = 0
}