package com.dreamit.androidquiz.quizlist

import com.dreamit.androidquiz.quizlist.model.Quizzes

interface QuizzesContract {

    interface View {
        fun showQuizzes(quizzes: Quizzes)

        fun showNextQuizzes(quizzes: Quizzes)

        fun showError(error: String)
    }

    interface Presenter {
        fun getQuizzes()

        fun getNextQuizzes(startFrom: Int)
    }

}