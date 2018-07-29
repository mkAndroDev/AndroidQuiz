package com.dreamit.androidquiz.data.quizzes.local

import com.dreamit.androidquiz.data.quizzes.QuizzesDataSource
import com.dreamit.androidquiz.quizlist.model.Quizzes
import io.reactivex.Observable
import io.reactivex.Observer
import io.realm.Realm

class LocalQuizzesRepository(private val localStorage: Realm) : QuizzesDataSource {

    override fun getQuizzes(): Observable<Quizzes> {
        val quizzes = localStorage.where(Quizzes::class.java).findFirst()//contains("page", 0).findFirst()
        return object : Observable<Quizzes>() {
            override fun subscribeActual(observer: Observer<in Quizzes>) {
                quizzes?.let {
                    observer.onNext(localStorage.copyFromRealm(it))
                } ?: observer.onError(Throwable("No elements in Realm"))
            }
        }
    }

    override fun getNextQuizzes(page: Int): Observable<Quizzes> {
        val quizzes = localStorage.where(Quizzes::class.java).contains("page", page.toString()).findFirst()
        return object : Observable<Quizzes>() {
            override fun subscribeActual(observer: Observer<in Quizzes>) {
                quizzes?.let {
                    observer.onNext(localStorage.copyFromRealm(it))
                } ?: observer.onError(Throwable("No elements in Realm"))
            }
        }
    }

    override fun saveQuizzes(quizzes: Quizzes) {
        localStorage.executeTransaction {
            it.copyToRealmOrUpdate(quizzes)
            it.close()
        }
    }
}