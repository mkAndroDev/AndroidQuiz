package com.dreamit.androidquiz.quizsolving

import com.dreamit.androidquiz.quizitem.model.QuizDetails

interface QuizSolvingContract {

    interface View {
        fun showQuizSolving(quizDetails: QuizDetails)

        fun showError(error: String)
    }

    interface Presenter {
        fun getQuizSolve(quizId: Long)

        fun saveQuizSolve(quizDetails: QuizDetails)
    }

}