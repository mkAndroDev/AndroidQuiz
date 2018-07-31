package com.dreamit.androidquiz.quizsolving.presenter

import com.dreamit.androidquiz.data.quizitem.QuizDetailsRepository
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import com.dreamit.androidquiz.quizsolving.QuizSolvingContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizSolvingPresenter(
        private val quizDetailsRepository: QuizDetailsRepository,
        private val view: QuizSolvingContract.View
) : QuizSolvingContract.Presenter {
    override fun getQuizSolve(quizId: Long) {
        quizDetailsRepository.getQuizSolve(quizId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ quizDetails ->
                    view.showQuizSolving(quizDetails)
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun saveQuizSolve(quizDetails: QuizDetails) {
        quizDetailsRepository.saveQuizDetails(quizDetails)
    }
}