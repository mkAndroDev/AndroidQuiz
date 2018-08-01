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
                    view.showQuizzes(quizzes)
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun getNextQuizzes(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}