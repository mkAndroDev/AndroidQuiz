package com.dreamit.androidquiz.data.quizzes.remote

import android.util.Log
import com.dreamit.androidquiz.data.quizzes.QuizzesDataSource
import com.dreamit.androidquiz.net.QuizService
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable

class RemoteQuizzesRepository(private val remoteRepository: QuizService) : QuizzesDataSource {

    override fun getQuizzes(): Observable<Quizzes> = remoteRepository.getQuizzes("0", "100")

    override fun getNextQuizzes(page: Int): Observable<Quizzes> = remoteRepository.getQuizzes(page.toString(), "100")

    override fun saveQuizzes(quizzes: Quizzes) {
        Log.e(TAG, "Not implemented!")
    }

    companion object {
        private val TAG = RemoteQuizzesRepository::class.java.simpleName
    }
}