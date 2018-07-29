package com.dreamit.androidquiz.quizlist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuizItem : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Long = 0
    @Expose
    @SerializedName("buttonStart")
    var buttonStart: String = ""
    @Expose
    @SerializedName("shareTitle")
    var shareTitle: String = ""
    @Expose
    @SerializedName("questions")
    var questions: Int = 0
    @Expose
    @SerializedName("createdAt")
    var createdAt: String = ""
    @Expose
    @SerializedName("sponsored")
    var sponsored: Boolean = false
    @Expose
    @SerializedName("categories")
    var categories: RealmList<CategoriesItem> = RealmList()
    @Expose
    @SerializedName("title")
    var title: String = ""
    @Expose
    @SerializedName("type")
    var type: String = ""
    @Expose
    @SerializedName("content")
    var content: String = ""
    @Expose
    @SerializedName("mainPhoto")
    var mainPhoto: MainPhoto? = null
    @Expose
    @SerializedName("tags")
    var tags: RealmList<Tag> = RealmList()
    @Expose
    @SerializedName("category")
    var category: Category? = null

}