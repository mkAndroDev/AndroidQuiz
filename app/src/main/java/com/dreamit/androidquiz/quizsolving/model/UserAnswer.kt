package com.dreamit.androidquiz.quizsolving.model

import io.realm.RealmObject

open class UserAnswer : RealmObject() {

    var questionId = 0
    var answerId = 0
}