package com.dreamit.androidquiz.data.quizitem

import com.dreamit.androidquiz.data.quizitem.local.LocalQuizDetailsRepository
import com.dreamit.androidquiz.data.quizitem.remote.RemoteQuizDetailsRepository
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import io.reactivex.Observable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class QuizDetailsRepository(
        private val localRepository: LocalQuizDetailsRepository,
        private val remoteRepository: RemoteQuizDetailsRepository
) : QuizDetailsDataSource {

    override fun getQuizDetails(quizId: Long): Observable<QuizDetails> =
            Observable.concat(
                    localRepository.getQuizDetails(quizId),
                    remoteRepository.getQuizDetails(quizId).doOnNext {
                        launch(UI) {
                            if (it.questions.isNotEmpty()) {
                                localRepository.saveQuizDetails(it)
                            }
                        }
                    }
            )

    override fun saveQuizDetails(quiz: QuizDetails) {
        localRepository.saveQuizDetails(quiz)
    }
}