package com.dreamit.androidquiz.data.quizzes.remote

import android.util.Log
import com.dreamit.androidquiz.data.quizzes.QuizzesDataSource
import com.dreamit.androidquiz.net.ConfigEndpoints.Companion.PER_PAGE
import com.dreamit.androidquiz.net.QuizService
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable

class RemoteQuizzesRepository(private val remoteRepository: QuizService) : QuizzesDataSource {

    override fun getQuizzes(): Observable<Quizzes> =
            remoteRepository.getQuizzes("0", PER_PAGE.toString())
                    .onErrorReturn {
                        Log.e(TAG, it.message)
                        Quizzes()
                    }

    override fun getNextQuizzes(startFrom: Int): Observable<Quizzes> =
            remoteRepository.getQuizzes(startFrom.toString(), PER_PAGE.toString())
                    .onErrorReturn {
                        Log.e(TAG, it.message)
                        Quizzes()
                    }

    override fun saveQuizzes(quizzes: Quizzes) {
        Log.e(TAG, "Not implemented!")
    }

    companion object {
        private val TAG = RemoteQuizzesRepository::class.java.simpleName
    }
}