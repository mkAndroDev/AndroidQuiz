package com.dreamit.androidquiz.net

import com.dreamit.androidquiz.quizitem.model.QuizDetails
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface QuizService {

    @GET("quizzes/{page}/{perPage}")
    fun getQuizzes(@Path("page") page: String, @Path("perPage") perPage: String): Observable<Quizzes>

    @GET("quiz/{id}/0")
    fun getQuiz(@Path("id") page: String): Observable<QuizDetails>

}