package com.dreamit.androidquiz.quizlist.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.androidquiz.R
import com.dreamit.androidquiz.quizlist.model.QuizItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.quizzes_item.view.*

class QuizzesAdapter : RecyclerView.Adapter<QuizzesAdapter.ViewHolder>() {

    private var quizzes: List<QuizItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.quizzes_item, parent, false)

        return ViewHolder(itemView, quizzes)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupWeather(position)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    fun addMoreEntries(newQuizzes: List<QuizItem>) {
        val allQuizzes = quizzes + newQuizzes
        DiffUtil
                .calculateDiff(QuizzesEntriesDiffCallback(quizzes, allQuizzes))
                .dispatchUpdatesTo(this)
        quizzes = allQuizzes
    }

    class ViewHolder(view: View, private val quizzes: List<QuizItem>) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_quizzes_item_name
        private val imageIv = view.iv_quizzes_item_image
        private val sponsoredTv = view.tv_quizzes_item_sponsored

        internal fun setupWeather(position: Int) {

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
        }
    }

    private class QuizzesEntriesDiffCallback(
            private val oldQuizzes: List<QuizItem>,
            private val newQuizzes: List<QuizItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldQuizzes.size

        override fun getNewListSize() = newQuizzes.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldQuizzes[oldItemPosition].id == newQuizzes[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldQuizzes[oldItemPosition] == newQuizzes[newItemPosition]
        }
    }
}