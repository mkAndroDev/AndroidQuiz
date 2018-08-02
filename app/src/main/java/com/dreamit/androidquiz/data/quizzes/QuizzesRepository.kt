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
                remoteQuizzesRepository.getQuizzes().doOnNext {
                    if (it.items.isNotEmpty()) {
                        launch(UI) {
                            localQuizzesRepository.saveQuizzes(it)
                        }
                    }
                },
                localQuizzesRepository.getQuizzes())
    }

    override fun getNextQuizzes(startFrom: Int): Observable<Quizzes> {
        return Observable.concat(
                remoteQuizzesRepository.getNextQuizzes(startFrom).doOnNext {
                    if (it.items.isNotEmpty()) {
                        launch(UI) {
                            localQuizzesRepository.saveQuizzes(it.apply {
                                quizzesFrom = startFrom.toLong()
                            })
                        }
                    }
                },
                localQuizzesRepository.getNextQuizzes(startFrom))
    }

    override fun saveQuizzes(quizzes: Quizzes) {
        localQuizzesRepository.saveQuizzes(quizzes)
    }
}