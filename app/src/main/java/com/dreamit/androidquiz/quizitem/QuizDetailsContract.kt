package com.dreamit.androidquiz.quizitem

import com.dreamit.androidquiz.quizitem.model.QuizDetails

interface QuizDetailsContract {

    interface View {
        fun showQuizDetails(quizDetails: QuizDetails)

        fun showError(error: String)
    }

    interface Presenter {
        fun getQuizDetails(quizId: Long)
    }

}