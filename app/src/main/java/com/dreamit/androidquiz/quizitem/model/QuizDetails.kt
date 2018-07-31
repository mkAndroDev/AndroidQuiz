package com.dreamit.androidquiz.quizitem.model

import com.dreamit.androidquiz.quizlist.model.CategoriesItem
import com.dreamit.androidquiz.quizlist.model.Category
import com.dreamit.androidquiz.quizlist.model.MainPhoto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuizDetails : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Long = 0
    @Expose
    @SerializedName("celebrity")
    var celebrity: Celebrity? = null
    @Expose
    @SerializedName("rates")
    var rates = RealmList<Rate>()
    @Expose
    @SerializedName("questions")
    var questions = RealmList<Question>()
    @Expose
    @SerializedName("createdAt")
    var createdAt = ""
    @Expose
    @SerializedName("sponsored")
    var sponsored = false
    @Expose
    @SerializedName("title")
    var title = ""
    @Expose
    @SerializedName("type")
    var type = ""
    @Expose
    @SerializedName("content")
    var content = ""
    @Expose
    @SerializedName("buttonStart")
    var buttonStart = ""
    @Expose
    @SerializedName("shareTitle")
    var shareTitle = ""
    @Expose
    @SerializedName("categories")
    var categories = RealmList<CategoriesItem>()
    @Expose
    @SerializedName("scripts")
    var scripts = ""
    @Expose
    @SerializedName("mainPhoto")
    var mainPhoto: MainPhoto? = null
    @Expose
    @SerializedName("category")
    var category: Category? = null
    @Expose
    @SerializedName("isBattle")
    var isBattle = false
    @Expose
    @SerializedName("created")
    var created = ""
    @Expose
    @SerializedName("latestResults")
    var latestResults = RealmList<Result>()
    @Expose
    @SerializedName("avgResult")
    var avgResult = 0.0
    @Expose
    @SerializedName("resultCount")
    var resultCount = 0
    @Expose
    @SerializedName("cityAvg")
    var cityAvg: String? = ""
    @Expose
    @SerializedName("cityTime")
    var cityTime: String? = ""
    @Expose
    @SerializedName("cityCount")
    var cityCount: String? = ""
    @Expose
    @SerializedName("userBattleDone")
    var userBattleDone = false
    @Expose
    @SerializedName("sponsoredResults")
    var sponsoredResults: SponsoredResults? = null

    companion object {
        const val QUIZ_DETAILS_FIELD_ID = "id"
    }
}