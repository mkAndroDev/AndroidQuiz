package com.dreamit.androidquiz.quizitem

import com.dreamit.androidquiz.quizitem.model.QuizDetails
import com.dreamit.androidquiz.quizsolving.model.QuizSolve

interface QuizDetailsContract {

    interface View {
        fun showQuizDetails(quizDetails: QuizDetails)

        fun showQuizSolve(quizSolve: QuizSolve)

        fun showError(error: String)
    }

    interface Presenter {
        fun getQuizDetails(quizId: Long)

        fun getQuizSolve(quizId: Long)

        fun saveQuizSolve(quizSolve: QuizSolve)
    }

}