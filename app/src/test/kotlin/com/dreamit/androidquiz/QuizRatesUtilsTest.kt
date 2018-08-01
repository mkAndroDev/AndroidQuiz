package com.dreamit.androidquiz

import com.dreamit.androidquiz.quizitem.model.Answer
import com.dreamit.androidquiz.quizitem.model.Rate
import com.dreamit.androidquiz.quizsolving.model.UserAnswer
import com.dreamit.androidquiz.utils.QuizRatesUtils
import kotlin.test.Test
import kotlin.test.assertTrue

class QuizRatesUtilsTest {

    @Test
    fun getResultAsPercentsWorksProperly() {
        /* Given */
        val validAnswers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val maxScore = 10
        /* When */
        val results = mutableListOf<Int>().apply {
            validAnswers.forEach {
                add(QuizRatesUtils.getValidResultAsPercent(it, maxScore))
            }
        }
        /* Then */
        assertTrue { results[0] == 0 }
        assertTrue { results[1] == 10 }
        assertTrue { results[2] == 20 }
        assertTrue { results[3] == 30 }
        assertTrue { results[4] == 40 }
        assertTrue { results[5] == 50 }
        assertTrue { results[6] == 60 }
        assertTrue { results[7] == 70 }
        assertTrue { results[8] == 80 }
        assertTrue { results[9] == 90 }
        assertTrue { results[10] == 100 }
    }

    @Test
    fun getRateTextWorksProperly() {
        /* Given */
        val rates = getPreparedRates()
        val percentScores = listOf(0, 23, 45, 67, 100)
        /* When */
        val results = mutableListOf<String>().apply {
            percentScores.forEach {
                add(QuizRatesUtils.getRateText(it, rates))
            }
        }
        /* Then */
        assertTrue { results[0] == "0-20" }
        assertTrue { results[1] == "21-40" }
        assertTrue { results[2] == "41-60" }
        assertTrue { results[3] == "61-80" }
        assertTrue { results[4] == "81-100" }
    }

    @Test
    fun isAnswerCorrectWorksProperly() {
        /* Given */
        val userAnswers = mutableListOf<UserAnswer>().apply {
            add(UserAnswer().apply {
                questionId = 0
                answerId = 2
            })
            add(UserAnswer().apply {
                questionId = 1
                answerId = 1
            })
            add(UserAnswer().apply {
                questionId = 2
                answerId = 1
            })
        }
        /* When */
        val result1 = QuizRatesUtils.isAnswerCorrect(userAnswers[0].answerId, getAnswers1())
        val result2 = QuizRatesUtils.isAnswerCorrect(userAnswers[1].answerId, getAnswers2())
        val result3 = QuizRatesUtils.isAnswerCorrect(userAnswers[2].answerId, getAnswers3())
        /* Then */
        assertTrue { result1 == true }
        assertTrue { result2 == false }
        assertTrue { result3 == true }
    }

    private fun getPreparedRates(): List<Rate> {
        return mutableListOf<Rate>().apply {
            add(Rate().apply {
                from = 0
                to = 20
                content = "0-20"
            })
            add(Rate().apply {
                from = 21
                to = 40
                content = "21-40"
            })
            add(Rate().apply {
                from = 41
                to = 60
                content = "41-60"
            })
            add(Rate().apply {
                from = 61
                to = 80
                content = "61-80"
            })
            add(Rate().apply {
                from = 81
                to = 100
                content = "81-100"
            })
        }
    }

    private fun getAnswers1(): List<Answer> {
        return mutableListOf<Answer>().apply {
            add(0, Answer().apply {
                isCorrect = 0
            })
            add(1, Answer().apply {
                isCorrect = 0
            })
            add(2, Answer().apply {
                isCorrect = 1
            })
        }
    }

    private fun getAnswers2(): List<Answer> {
        return mutableListOf<Answer>().apply {
            add(0, Answer().apply {
                isCorrect = 1
            })
            add(1, Answer().apply {
                isCorrect = 0
            })
            add(2, Answer().apply {
                isCorrect = 0
            })
        }
    }

    private fun getAnswers3(): List<Answer> {
        return mutableListOf<Answer>().apply {
            add(0, Answer().apply {
                isCorrect = 0
            })
            add(1, Answer().apply {
                isCorrect = 1
            })
            add(2, Answer().apply {
                isCorrect = 0
            })
        }
    }

}