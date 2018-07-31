package com.dreamit.androidquiz.quizitem.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.androidquiz.MainActivity
import com.dreamit.androidquiz.QuizApp
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.data.quizitem.QuizDetailsRepository
import com.dreamit.androidquiz.data.quizitem.local.LocalQuizDetailsRepository
import com.dreamit.androidquiz.data.quizitem.remote.RemoteQuizDetailsRepository
import com.dreamit.androidquiz.net.ConfigEndpoints
import com.dreamit.androidquiz.net.RestClient
import com.dreamit.androidquiz.quizitem.QuizDetailsContract
import com.dreamit.androidquiz.quizitem.model.QuizDetails
import com.dreamit.androidquiz.quizitem.presenter.QuizDetailsPresenter
import com.dreamit.androidquiz.quizsolving.view.QuizSolvingFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz_details.*

class QuizDetailsFragment : Fragment(), QuizDetailsContract.View {

    var quizId: Long = 0
    private lateinit var quizDetailsRepository: QuizDetailsRepository
    private val presenter: QuizDetailsContract.Presenter by lazy {
        QuizDetailsPresenter(quizDetailsRepository, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()

        presenter.getQuizDetails(quizId)
    }

    private fun initPresenter() {
        val quizDetailsService = RestClient(ConfigEndpoints.BASE_URL).setupRestClient()
        val remoteQuizDetailsRepository = RemoteQuizDetailsRepository(quizDetailsService)
        val app = activity?.application as QuizApp
        val localQuizDetailsRepository = LocalQuizDetailsRepository(app.realm)
        quizDetailsRepository = QuizDetailsRepository(localQuizDetailsRepository, remoteQuizDetailsRepository)
    }

    override fun showQuizDetails(quizDetails: QuizDetails) {
        tv_quiz_details_name.text = quizDetails.title
        quizDetails.category?.let {
            tv_quiz_details_category.text = it.name
        }
        tv_quiz_details_description.text = quizDetails.content

        quizDetails.mainPhoto?.let {
            Picasso.get()
                    .load(it.url)
                    .fit()
                    .centerCrop()
                    .into(iv_quiz_details_image)
        }

        tv_start_quiz.setOnClickListener {
            openQuizSolve()
        }
        tv_go_back_to_quiz.setOnClickListener {
            openQuizSolve()
        }
    }

    private fun openQuizSolve() {
        activity?.let {
            QuizSolvingFragment().apply {
                quizId = this@QuizDetailsFragment.quizId
                (it as MainActivity).loadFragment(this, true)
            }
        }
    }

    override fun showError(error: String) {
        Log.e(TAG, error)
    }

    companion object {
        private val TAG = QuizDetailsFragment::class.java.simpleName
    }
}
