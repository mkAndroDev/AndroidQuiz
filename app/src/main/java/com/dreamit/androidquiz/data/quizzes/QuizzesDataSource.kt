package com.dreamit.androidquiz.data.quizzes

import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable

interface QuizzesDataSource {

    fun getQuizzes(): Observable<Quizzes>

    fun getNextQuizzes(page: Int): Observable<Quizzes>

    fun saveQuizzes(quizzes: Quizzes)

}