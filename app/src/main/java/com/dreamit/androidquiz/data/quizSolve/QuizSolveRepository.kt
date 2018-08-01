package com.dreamit.androidquiz.data.quizSolve

import com.dreamit.androidquiz.data.quizSolve.local.LocalQuizSolveRepository
import com.dreamit.androidquiz.quizsolving.model.QuizSolve
import io.reactivex.Observable

class QuizSolveRepository(
        private val localQuizSolveRepository: LocalQuizSolveRepository
) : QuizSolveDataSource {

    override fun getQuizSolve(quizId: Long): Observable<QuizSolve> =
            localQuizSolveRepository.getQuizSolve(quizId)

    override fun saveQuizSolve(quizSolve: QuizSolve) {
        localQuizSolveRepository.saveQuizSolve(quizSolve)
    }
}