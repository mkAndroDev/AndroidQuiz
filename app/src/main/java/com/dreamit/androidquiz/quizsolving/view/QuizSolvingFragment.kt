package com.dreamit.androidquiz.quizsolving.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dreamit.androidquiz.QuizApp
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.data.quizitem.QuizDetailsRepository
import com.dreamit.androidquiz.data.quizitem.local.LocalQuizDetailsRepository
import com.dreamit.androidquiz.data.quizitem.remote.RemoteQuizDetailsRepository
import com.dreamit.androidquiz.net.ConfigEndpoints
import com.dreamit.androidquiz.net.RestClient
import com.dreamit.androidquiz.quizitem.model.Question
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import com.dreamit.androidquiz.quizsolving.QuizSolvingContract
import com.dreamit.androidquiz.quizsolving.adapter.QuizAnswersAdapter
import com.dreamit.androidquiz.quizsolving.presenter.QuizSolvingPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz_solving.*

class QuizSolvingFragment : Fragment(), QuizSolvingContract.View, QuizAnswersAdapter.OnQuizAnswerAdapterListener {

    var quizId: Long = 0
    private lateinit var quizDetailsRepository: QuizDetailsRepository
    private val presenter: QuizSolvingContract.Presenter by lazy {
        QuizSolvingPresenter(quizDetailsRepository, this)
    }
    private lateinit var quizSolve: QuizDetails
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
        val quizDetailsService = RestClient(ConfigEndpoints.BASE_URL).setupRestClient()
        val remoteQuizDetailsRepository = RemoteQuizDetailsRepository(quizDetailsService)
        val app = activity?.application as QuizApp
        val localQuizDetailsRepository = LocalQuizDetailsRepository(app.realm)
        quizDetailsRepository = QuizDetailsRepository(localQuizDetailsRepository, remoteQuizDetailsRepository)
    }

    override fun showQuizSolving(quizDetails: QuizDetails) {
        pb_quiz_solving.apply {
            max = quizDetails.questions.size
        }
        quizSolve = quizDetails
        showQuizAnswer(quizSolve.questions.first {
            it.userAnswer == null
        })
    }

    private fun showNextQuestion() {
        val answersIndex = pb_quiz_solving.progress

        if (pb_quiz_solving.progress == quizSolve.questions.lastIndex + 1) {
            showFinish()
        } else {
            showQuizAnswer(quizSolve.questions[answersIndex]!!)
        }
    }

    private fun showQuizAnswer(question: Question) {
        pb_quiz_solving.apply {
            progress = quizSolve.questions.indexOf(question) + 1
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
    }

    private fun showFinish() {
        var validQuestionsCount = 0

        quizSolve.questions.forEach { question ->
            question.userAnswer?.let {
                if (question.answers[it]?.isCorrect == 1) {
                    validQuestionsCount++
                }
            }
        }

        Toast.makeText(context, "Poprawnych odpowiedzi: $validQuestionsCount", Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Log.e(TAG, error)
    }

    override fun onQuizClicked(id: Int) {
        quizSolve.questions[pb_quiz_solving.progress - 1]?.userAnswer = id
        showNextQuestion()
    }

    companion object {
        private val TAG = QuizSolvingFragment::class.java.simpleName
    }
}
