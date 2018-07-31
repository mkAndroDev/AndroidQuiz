package com.dreamit.androidquiz.quizitem.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Question : RealmObject() {

    @Expose
    @SerializedName("image")
    var image: Image? = null
    @Expose
    @SerializedName("answers")
    var answers = RealmList<Answer>()
    @Expose
    @SerializedName("type")
    var type = ""
    @Expose
    @SerializedName("text")
    var text = ""
    @Expose
    @SerializedName("title")
    var title = ""
    @Expose
    @SerializedName("order")
    var order = 0
}