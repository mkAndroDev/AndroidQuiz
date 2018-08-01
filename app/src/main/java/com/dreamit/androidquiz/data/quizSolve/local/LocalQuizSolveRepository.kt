package com.dreamit.androidquiz.data.quizSolve.local

import com.dreamit.androidquiz.data.quizSolve.QuizSolveDataSource
import com.dreamit.androidquiz.quizsolving.model.QuizSolve
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.runBlocking

class LocalQuizSolveRepository(private val localStorage: Realm) : QuizSolveDataSource {

    override fun getQuizSolve(quizId: Long): Observable<QuizSolve> =
            Observable.fromCallable {
                getQuizSolveFromDatabase(quizId)
            }

    private fun getQuizSolveFromDatabase(quizId: Long): QuizSolve =
            runBlocking(UI) {
                val quizSolve = localStorage.where(QuizSolve::class.java)
                        .equalTo(QuizSolve.QUIZ_SOLVE_ID_NAME, quizId)
                        .findFirst()

                quizSolve?.let {
                    localStorage.copyFromRealm(it)
                } ?: QuizSolve()
            }

    override fun saveQuizSolve(quizSolve: QuizSolve) {
        localStorage.executeTransaction {
            it.copyToRealmOrUpdate(quizSolve)
        }
    }
}