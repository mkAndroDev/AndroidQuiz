package com.dreamit.androidquiz.quizsolving.view

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.androidquiz.MainActivity
import com.dreamit.androidquiz.QuizApp
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.data.quizSolve.QuizSolveRepository
import com.dreamit.androidquiz.data.quizSolve.local.LocalQuizSolveRepository
import com.dreamit.androidquiz.quizitem.model.Question
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import com.dreamit.androidquiz.quizlist.view.QuizzesFragment
import com.dreamit.androidquiz.quizsolving.QuizSolvingContract
import com.dreamit.androidquiz.quizsolving.adapter.QuizAnswersAdapter
import com.dreamit.androidquiz.quizsolving.model.QuizSolve
import com.dreamit.androidquiz.quizsolving.model.UserAnswer
import com.dreamit.androidquiz.quizsolving.presenter.QuizSolvingPresenter
import com.dreamit.androidquiz.utils.QuizRatesUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz_solving.*

class QuizSolvingFragment : Fragment(), QuizSolvingContract.View, QuizAnswersAdapter.OnQuizAnswerAdapterListener {

    var quizId: Long = 0
    var status: Int = 0
    private lateinit var quizSolveRepository: QuizSolveRepository
    private val presenter: QuizSolvingContract.Presenter by lazy {
        QuizSolvingPresenter(quizSolveRepository, this)
    }
    private lateinit var quizSolve: QuizSolve
    private lateinit var quizDetails: QuizDetails
    private var quizAnswersAdapter = QuizAnswersAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_solving, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initRecyclerView()

        presenter.getQuizSolve(quizId)
    }

    private fun initRecyclerView() {
        rv_quiz_solve_answers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = quizAnswersAdapter
        }
    }

    private fun initPresenter() {
        val app = activity?.application as QuizApp
        val localRepository = LocalQuizSolveRepository(app.realm)
        quizSolveRepository = QuizSolveRepository(localRepository)
    }

    override fun showQuizSolving(quizSolve: QuizSolve) {
        pb_quiz_solving.apply {
            max = quizSolve.quizDetails?.questions?.size ?: 0
        }
        this.quizSolve = quizSolve
        this.quizDetails = quizSolve.quizDetails?.let {
            it
        } ?: QuizDetails()
        if (status == STATUS_NEW) {
            presenter.clearQuizSolve(quizSolve)
            showQuizAnswer(quizDetails.questions.first())
        } else {
            val indexOfLastChecked = quizSolve.userAnswers.size
            showQuizAnswer(quizDetails.questions[indexOfLastChecked])
        }
    }

    private fun showNextQuestion() {
        val answersIndex = pb_quiz_solving.progress
        if (pb_quiz_solving.progress == quizDetails.questions.lastIndex + 1) {
            finishQuizSolving()
        } else {
            showQuizAnswer(quizDetails.questions[answersIndex])
        }
    }

    private fun showQuizAnswer(question: Question?) {
        question?.let {
            pb_quiz_solving.apply {
                progress = quizDetails.questions.indexOf(question) + 1
            }
            tv_quiz_solving_name.text = if (question.text.isNotEmpty()) question.text else question.text

            question.image?.let {
                if (it.url.isNotEmpty()) {
                    Picasso.get()
                            .load(it.url)
                            .fit()
                            .centerCrop()
                            .into(iv_quiz_solving_image)
                }
            }

            quizAnswersAdapter = QuizAnswersAdapter(this).apply {
                changeAnswers(question.answers)
            }
            rv_quiz_solve_answers.adapter = quizAnswersAdapter
        } ?: showError(getString(R.string.quiz_solving_empty_error))
    }

    private fun finishQuizSolving() {
        var validQuestionsCount = 0

        quizSolve.userAnswers.forEach { userAnswer ->
            quizDetails.questions[userAnswer.questionId]?.answers?.let {
                if (QuizRatesUtils.isAnswerCorrect(userAnswer.answerId, it)) {
                    validQuestionsCount++
                }
            }
        }

        var ratesTitle = getString(R.string.quiz_solving_finish_rate) + " $validQuestionsCount \n"
        val validQuestionsAsPercent = QuizRatesUtils.getValidResultAsPercent(validQuestionsCount, quizDetails.questions.size)
        ratesTitle += QuizRatesUtils.getRateText(validQuestionsAsPercent, quizDetails.rates)
        showFinishDialog(ratesTitle)
        presenter.clearQuizSolve(quizSolve)
    }

    private fun showFinishDialog(message: String) {
        AlertDialog.Builder(context)
                .setTitle(R.string.quiz_solving_finish_label)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    openQuizzesFragment()
                })
                .create()
                .show()
    }

    private fun openQuizzesFragment() {
        activity?.let {
            (it as MainActivity).loadFragment(QuizzesFragment(), false)
        }
    }

    override fun showError(error: String) {
        Log.e(TAG, error)
        openQuizzesFragment()
    }

    override fun onQuizClicked(id: Int) {
        quizSolve.userAnswers.add(UserAnswer().apply {
            questionId = pb_quiz_solving.progress - 1
            answerId = id
        })
        presenter.saveQuizSolve(quizSolve)
        showNextQuestion()
    }

    companion object {
        private val TAG = QuizSolvingFragment::class.java.simpleName
        const val STATUS_NEW = 0
        const val STATUS_RETURN = 1
    }
}
