package com.dreamit.androidquiz.data.quizSolve

import com.dreamit.androidquiz.quizsolving.model.QuizSolve
import io.reactivex.Observable

interface QuizSolveDataSource {

    fun getQuizSolve(quizId: Long): Observable<QuizSolve>

    fun saveQuizSolve(quizSolve: QuizSolve)

}