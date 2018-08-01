package com.dreamit.androidquiz.data.quizitem.remote

import android.util.Log
import com.dreamit.androidquiz.data.quizitem.QuizDetailsDataSource
import com.dreamit.androidquiz.net.QuizService
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import io.reactivex.Observable

class RemoteQuizDetailsRepository(private val remoteRepository: QuizService) : QuizDetailsDataSource {

    override fun getQuizDetails(quizId: Long): Observable<QuizDetails> =
            remoteRepository.getQuiz(quizId.toString())
                    .onErrorReturn {
                        Log.e(TAG, it.message)
                        QuizDetails()
                    }

    override fun saveQuizDetails(quiz: QuizDetails) {
        Log.e(TAG, "Not implemented!")
    }

    override fun getQuizSolve(quizId: Long): Observable<QuizDetails> =
            Observable.fromCallable {
                QuizDetails()
            }

    companion object {
        private val TAG = RemoteQuizDetailsRepository::class.java.simpleName
    }
}