package com.dreamit.androidquiz.data.quizzes

import com.dreamit.androidquiz.data.quizzes.local.LocalQuizzesRepository
import com.dreamit.androidquiz.data.quizzes.remote.RemoteQuizzesRepository
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class QuizzesRepository(
        private val localQuizzesRepository: LocalQuizzesRepository,
        private val remoteQuizzesRepository: RemoteQuizzesRepository
) : QuizzesDataSource {

    override fun getQuizzes(): Observable<Quizzes> {
        return Observable.concat(
                localQuizzesRepository.getQuizzes(),
                remoteQuizzesRepository.getQuizzes().doOnNext {
                    launch(UI) {
                        if (it.items.isNotEmpty())
                            localQuizzesRepository.saveQuizzes(it)
                    }
                }
        )
    }

    override fun getNextQuizzes(page: Int): Observable<Quizzes> {
        return Observable.merge(
                remoteQuizzesRepository.getNextQuizzes(page),
                localQuizzesRepository.getNextQuizzes(page))
    }

    override fun saveQuizzes(quizzes: Quizzes) {
        localQuizzesRepository.saveQuizzes(quizzes)
    }
}