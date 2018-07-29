package com.dreamit.androidquiz.quizlist

import com.dreamit.androidquiz.quizlist.model.Quizzes

interface QuizzesContract {

    interface View {
        fun showQuizzes(quizzes: Quizzes)

        fun showError(error: String)
    }

    interface Presenter {
        fun getQuizzes()

        fun getNextQuizzes(page: Int)
    }

}