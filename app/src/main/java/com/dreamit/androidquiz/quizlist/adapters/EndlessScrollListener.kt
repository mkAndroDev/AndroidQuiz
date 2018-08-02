package com.dreamit.androidquiz.quizlist.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dreamit.androidquiz.net.ConfigEndpoints.Companion.PER_PAGE

abstract class EndlessScrollListener(
        private val layoutManager: RecyclerView.LayoutManager
) : RecyclerView.OnScrollListener() {
    var visibleThreshold = 5
    var startFrom = 0
    var previousTotalItemCount = 0
    var loading = true
    var firstStartFromIndex = 0

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition: Int = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (totalItemCount < previousTotalItemCount) {
            startFrom = firstStartFromIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && (totalItemCount >= previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (dy > 0 && !loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            startFrom += PER_PAGE + 1
            onLoadMore(startFrom, totalItemCount, view)
            loading = true
        }
    }

    fun resetState() {
        startFrom = firstStartFromIndex
        previousTotalItemCount = 0
        loading = true
    }

    abstract fun onLoadMore(startFrom: Int, totalItemsCount: Int, view: RecyclerView)
}