package com.dreamit.androidquiz.data.quizitem.local

import com.dreamit.androidquiz.data.quizitem.QuizDetailsDataSource
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.runBlocking

class LocalQuizDetailsRepository(private val localStorage: Realm) : QuizDetailsDataSource {

    override fun getQuizDetails(quizId: Long): Observable<QuizDetails> =
            Observable.fromCallable {
                getQuizDetailsFromDatabase(quizId)
            }

    private fun getQuizDetailsFromDatabase(quizId: Long): QuizDetails =
            runBlocking(UI) {
                val quizDetails = localStorage.where(QuizDetails::class.java)
                        .equalTo(QuizDetails.QUIZ_DETAILS_FIELD_ID, quizId)
                        .findFirst()

                quizDetails?.let {
                    localStorage.copyFromRealm(quizDetails)
                } ?: QuizDetails()
            }

    override fun getQuizSolve(quizId: Long): Observable<QuizDetails> =
            Observable.fromCallable {
                getQuizDetailsFromDatabase(quizId)
            }

    override fun saveQuizDetails(quiz: QuizDetails) {
        localStorage.executeTransaction {
            it.copyToRealmOrUpdate(quiz)
        }
    }
}