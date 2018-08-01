package com.dreamit.androidquiz.quizsolving.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.quizitem.model.Answer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.quiz_solve_answers_item.view.*

class QuizAnswersAdapter(
        private val listener: QuizAnswersAdapter.OnQuizAnswerAdapterListener
) : RecyclerView.Adapter<QuizAnswersAdapter.ViewHolder>() {

    private var answers: List<Answer> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.quiz_solve_answers_item, parent, false)

        return ViewHolder(itemView, answers, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupAnswers(position)
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun changeAnswers(newAnswers: List<Answer>) {
        answers = newAnswers
    }

    class ViewHolder(
            view: View,
            private val answers: List<Answer>,
            private val listener: OnQuizAnswerAdapterListener
    ) : RecyclerView.ViewHolder(view) {

        private val itemCl = view.cl_quiz_answer_item
        private val nameTv = view.tv_quiz_answer_name
        private val imageIv = view.iv_quiz_answer_image

        internal fun setupAnswers(position: Int) {

            val answer = answers[position]

            nameTv.text = answer.text
            answer.image?.let {
                if (it.url.isNotBlank()) {
                    Picasso.get()
                            .load(it.url)
                            .fit()
                            .centerCrop()
                            .into(imageIv)
                }
            }
            itemCl.setOnClickListener {
                listener.onQuizClicked(answers.indexOf(answer))
            }
        }
    }

    interface OnQuizAnswerAdapterListener {
        fun onQuizClicked(id: Int)
    }
}