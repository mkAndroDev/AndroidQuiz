package com.dreamit.androidquiz.quizitem.presenter

import com.dreamit.androidquiz.data.quizSolve.QuizSolveRepository
import com.dreamit.androidquiz.data.quizitem.QuizDetailsRepository
import com.dreamit.androidquiz.quizitem.QuizDetailsContract
import com.dreamit.androidquiz.quizsolving.model.QuizSolve
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizDetailsPresenter(
        private val quizDetailsRepository: QuizDetailsRepository,
        private val quizSolveRepository: QuizSolveRepository,
        private val view: QuizDetailsContract.View
) : QuizDetailsContract.Presenter {

    override fun getQuizDetails(quizId: Long) {
        quizDetailsRepository.getQuizDetails(quizId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ quizDetails ->
                    view.showQuizDetails(quizDetails)
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun getQuizSolve(quizId: Long) {
        quizSolveRepository.getQuizSolve(quizId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ quizSolve ->
                    view.showQuizSolve(quizSolve)
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun saveQuizSolve(quizSolve: QuizSolve) {
        quizSolveRepository.saveQuizSolve(quizSolve)
    }
}