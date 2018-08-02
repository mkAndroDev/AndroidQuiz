package com.dreamit.androidquiz.quizlist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Quizzes : RealmObject() {

    @PrimaryKey
    var quizzesFrom: Long = 0
    @Expose
    @SerializedName("count")
    var quizzesCount: Int = 0
    @Expose
    @SerializedName("items")
    var items: RealmList<QuizItem> = RealmList()

    companion object {
        const val QUIZZES_FROM_NAME = "quizzesFrom"
    }
}