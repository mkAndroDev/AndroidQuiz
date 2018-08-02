package com.dreamit.androidquiz.quizlist.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.androidquiz.MainActivity
import com.dreamit.androidquiz.QuizApp
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.data.quizzes.QuizzesRepository
import com.dreamit.androidquiz.data.quizzes.local.LocalQuizzesRepository
import com.dreamit.androidquiz.data.quizzes.remote.RemoteQuizzesRepository
import com.dreamit.androidquiz.net.ConfigEndpoints
import com.dreamit.androidquiz.net.RestClient
import com.dreamit.androidquiz.quizitem.view.QuizDetailsFragment
import com.dreamit.androidquiz.quizlist.QuizzesContract
import com.dreamit.androidquiz.quizlist.adapters.EndlessScrollListener
import com.dreamit.androidquiz.quizlist.adapters.QuizzesAdapter
import com.dreamit.androidquiz.quizlist.model.Quizzes
import com.dreamit.androidquiz.quizlist.presenter.QuizzesPresenter
import kotlinx.android.synthetic.main.fragment_quizzes.*

class QuizzesFragment : Fragment(), QuizzesContract.View, QuizzesAdapter.OnQuizzesAdapterListener {

    private lateinit var quizRepo: QuizzesRepository
    private val presenter: QuizzesContract.Presenter by lazy {
        QuizzesPresenter(quizRepo, this)
    }
    private val quizzesAdapter = QuizzesAdapter(this)
    private lateinit var layoutManager: LinearLayoutManager
    private val endlessPage: EndlessScrollListener by lazy {
        object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(startFrom: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.getNextQuizzes(startFrom)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizzes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initViews()

        presenter.getQuizzes()
    }

    private fun initPresenter() {
        val quizService = RestClient(ConfigEndpoints.BASE_URL).setupRestClient()
        val remoteQuizzesRepository = RemoteQuizzesRepository(quizService)
        val app = activity?.application as QuizApp
        val localQuizzesRepository = LocalQuizzesRepository(app.realm)
        quizRepo = QuizzesRepository(localQuizzesRepository, remoteQuizzesRepository)
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(context)
        rv_quizzes.apply {
            setHasFixedSize(true)
            layoutManager = this@QuizzesFragment.layoutManager
            adapter = quizzesAdapter
            addOnScrollListener(endlessPage)
        }
    }

    override fun showQuizzes(quizzes: Quizzes) {
        quizzesAdapter.resetQuizzes(quizzes.items)
        endlessPage.resetState()
    }

    override fun showNextQuizzes(quizzes: Quizzes) {
        quizzesAdapter.addAll(quizzes.items)
        endlessPage.loading = true
    }

    override fun showError(error: String) {
        Log.e(TAG, error)
    }

    override fun onQuizClicked(id: Long) {
        activity?.let {
            QuizDetailsFragment().apply {
                quizId = id
                (it as MainActivity).loadFragment(this, true)
            }
        }
    }

    companion object {
        private val TAG = QuizzesFragment::class.java.simpleName
    }
}
