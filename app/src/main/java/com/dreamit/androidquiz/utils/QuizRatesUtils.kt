package com.dreamit.androidquiz.utils

import com.dreamit.androidquiz.quizitem.model.Answer
import com.dreamit.androidquiz.quizitem.model.Rate

class QuizRatesUtils {

    companion object {
        fun getValidResultAsPercent(validQuestionsCount: Int, totalAnswers: Int): Int {
            return validQuestionsCount * 100 / totalAnswers
        }

        fun getRateText(resultAsPercent: Int, rates: List<Rate>): String {
            return rates.find {
                resultAsPercent >= it.from && resultAsPercent <= it.to
            }?.content ?: ""
        }

        fun isAnswerCorrect(userAnswerId: Int, answers: List<Answer>): Boolean {
            return userAnswerId == answers.indexOf(answers.first { it.isCorrect == 1 })
        }
    }

}