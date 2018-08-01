package com.dreamit.androidquiz.data.quizitem

import com.dreamit.androidquiz.quizitem.model.QuizDetails
import io.reactivex.Observable

interface QuizDetailsDataSource {

    fun getQuizDetails(quizId: Long): Observable<QuizDetails>

    fun getQuizSolve(quizId: Long): Observable<QuizDetails>

    fun saveQuizDetails(quiz: QuizDetails)

}