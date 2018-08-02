package com.dreamit.androidquiz.quizlist.presenter

import com.dreamit.androidquiz.data.quizzes.QuizzesRepository
import com.dreamit.androidquiz.quizlist.QuizzesContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizzesPresenter(
        private val quizzesRepository: QuizzesRepository,
        private val view: QuizzesContract.View
) : QuizzesContract.Presenter {

    override fun getQuizzes() {
        quizzesRepository.getQuizzes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ quizzes ->
                    if (quizzes.items.isNotEmpty()) {
                        view.showQuizzes(quizzes)
                    }
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun getNextQuizzes(startFrom: Int) {
        quizzesRepository.getNextQuizzes(startFrom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ quizzes ->
                    if (quizzes.items.isNotEmpty()) {
                        view.showNextQuizzes(quizzes)
                    }
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }
}