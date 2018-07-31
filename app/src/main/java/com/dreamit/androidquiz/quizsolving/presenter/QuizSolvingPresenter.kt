package com.dreamit.androidquiz.quizsolving.presenter

import com.dreamit.androidquiz.data.quizSolve.QuizSolveRepository
import com.dreamit.androidquiz.quizsolving.QuizSolvingContract
import com.dreamit.androidquiz.quizsolving.model.QuizSolve
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizSolvingPresenter(
        private val quizSolveRepository: QuizSolveRepository,
        private val view: QuizSolvingContract.View
) : QuizSolvingContract.Presenter {
    override fun getQuizSolve(quizId: Long) {
        quizSolveRepository.getQuizSolve(quizId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ quizSolve ->
                    view.showQuizSolving(quizSolve)
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun saveQuizSolve(quizSolve: QuizSolve) {
        quizSolveRepository.saveQuizSolve(quizSolve)
    }
}