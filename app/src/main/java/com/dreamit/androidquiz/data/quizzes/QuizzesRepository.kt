package com.dreamit.androidquiz.data.quizzes

import com.dreamit.androidquiz.data.quizzes.local.LocalQuizzesRepository
import com.dreamit.androidquiz.data.quizzes.remote.RemoteQuizzesRepository
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable

class QuizzesRepository(
        private val localQuizzesRepository: LocalQuizzesRepository,
        private val remoteQuizzesRepository: RemoteQuizzesRepository
) : QuizzesDataSource {

    override fun getQuizzes(): Observable<Quizzes> {
        return Observable.merge(
                remoteQuizzesRepository.getQuizzes(),
                localQuizzesRepository.getQuizzes())
    }

    override fun getNextQuizzes(page: Int): Observable<Quizzes> {
        return Observable.merge(
                localQuizzesRepository.getNextQuizzes(page),
                remoteQuizzesRepository.getNextQuizzes(page))
    }

    override fun saveQuizzes(quizzes: Quizzes) {
        localQuizzesRepository.saveQuizzes(quizzes)
    }
}