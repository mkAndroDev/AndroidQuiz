package com.dreamit.androidquiz.data.quizzes.local

import com.dreamit.androidquiz.data.quizzes.QuizzesDataSource
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.runBlocking

class LocalQuizzesRepository(private val localStorage: Realm) : QuizzesDataSource {

    override fun getQuizzes(): Observable<Quizzes> =
            Observable.fromCallable {
                getQuizzesFromDatabase(0)
            }

    private fun getQuizzesFromDatabase(startFrom: Int): Quizzes =
            runBlocking(UI) {
                val quizzes = localStorage.where(Quizzes::class.java)
                        .equalTo(Quizzes.QUIZZES_FROM_NAME, startFrom)
                        .findFirst()

                quizzes?.let {
                    localStorage.copyFromRealm(quizzes)
                } ?: Quizzes()
            }

    override fun getNextQuizzes(startFrom: Int): Observable<Quizzes> =
            Observable.fromCallable {
                getQuizzesFromDatabase(startFrom)
            }

    override fun saveQuizzes(quizzes: Quizzes) {
        localStorage.executeTransactionAsync {
            it.insertOrUpdate(quizzes)
        }
    }
}