package com.dreamit.androidquiz.quizlist.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.quizlist.model.QuizItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.quizzes_item.view.*

class QuizzesAdapter(
        private val listener: QuizzesAdapter.OnQuizzesAdapterListener
) : RecyclerView.Adapter<QuizzesAdapter.ViewHolder>() {

    private var quizzes: List<QuizItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.quizzes_item, parent, false)

        return ViewHolder(itemView, quizzes, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupQuizzes(position)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    fun addMoreEntries(newQuizzes: List<QuizItem>) {
        if (newQuizzes.isNotEmpty()) {
            quizzes = newQuizzes
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
            view: View,
            private val quizzes: List<QuizItem>,
            private val listener: OnQuizzesAdapterListener
    ) : RecyclerView.ViewHolder(view) {

        private val itemCl = view.cl_quizzes_item
        private val nameTv = view.tv_quizzes_item_name
        private val imageIv = view.iv_quizzes_item_image
        private val sponsoredTv = view.tv_quizzes_item_sponsored

        internal fun setupQuizzes(position: Int) {

            val quiz = quizzes[position]

            nameTv.text = quiz.title
            quiz.mainPhoto?.let {
                if (it.url.isNotBlank()) {
                    Picasso.get()
                            .load(it.url)
                            .fit()
                            .centerCrop()
                            .into(imageIv)
                }
            }
            sponsoredTv.visibility = if (quiz.sponsored) View.VISIBLE else View.GONE
            itemCl.setOnClickListener {
                listener.onQuizClicked(quiz.id)
            }
        }
    }

    interface OnQuizzesAdapterListener {
        fun onQuizClicked(id: Long)
    }
}