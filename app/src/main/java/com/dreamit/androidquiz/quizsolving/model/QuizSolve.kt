package com.dreamit.androidquiz.quizsolving.model

import com.dreamit.androidquiz.quizitem.model.QuizDetails
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuizSolve : RealmObject() {

    @PrimaryKey
    var id: Long = 0
    var quizDetails:QuizDetails? = null
    var userAnswers = RealmList<UserAnswer>()

    companion object {
        const val QUIZ_SOLVE_ID_NAME = "id"
    }
}