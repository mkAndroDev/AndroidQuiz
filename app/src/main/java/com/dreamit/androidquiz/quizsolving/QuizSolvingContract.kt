package com.dreamit.androidquiz.quizsolving

import com.dreamit.androidquiz.quizsolving.model.QuizSolve

interface QuizSolvingContract {

    interface View {
        fun showQuizSolving(quizSolve: QuizSolve)

        fun showError(error: String)
    }

    interface Presenter {
        fun getQuizSolve(quizId: Long)

        fun saveQuizSolve(quizSolve: QuizSolve)
    }

}