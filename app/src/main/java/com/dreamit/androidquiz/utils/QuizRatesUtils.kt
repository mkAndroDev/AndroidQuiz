package com.dreamit.androidquiz.utils

class QuizRatesUtils {

    companion object {

        fun getValidResultAsPercent(validQuestionsCount: Int, totalAnswers: Int): Int {
            return validQuestionsCount / totalAnswers * 100
        }
    }

}