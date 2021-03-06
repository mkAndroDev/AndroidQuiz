package com.dreamit.androidquiz

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.dreamit.androidquiz.quizlist.view.QuizzesFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        loadFragment(QuizzesFragment(), false)
    }

    fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        if (window.currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
        }

        val fragmentTransaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, fragment, fragment::class.java.simpleName)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        } else {
            clearFragmentBackStack()
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun clearFragmentBackStack() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }
}
