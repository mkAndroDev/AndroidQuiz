package com.dreamit.androidquiz.quizitem.presenter

import com.dreamit.androidquiz.data.quizitem.QuizDetailsRepository
import com.dreamit.androidquiz.quizitem.QuizDetailsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizDetailsPresenter(
        private val quizDetailsRepository: QuizDetailsRepository,
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
}