package com.jccword.cvreader

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.jccword.cvreader.di.InjectableModelViewFactory
import com.jccword.cvreader.ui.NotificationUi
import com.jccword.cvreader.ui.ProgressUi
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : ProgressUi, NotificationUi, DaggerAppCompatActivity() {

    private lateinit var model: MainActivityViewModel
    @Inject
    lateinit var injectableModelViewFactory: InjectableModelViewFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener {
            println("[JCC] back stack changed: new size is ${supportFragmentManager.backStackEntryCount}")

            for(i in 0 until supportFragmentManager.backStackEntryCount)
                println("[JCC] Backstack @$i = ${supportFragmentManager.getBackStackEntryAt(i)}")
        }

        model = ViewModelProviders.of(this, injectableModelViewFactory).get(MainActivityViewModel::class.java)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun showProgress(show: Boolean) {
        runOnUiThread { progressBar.visibility = if (show) View.VISIBLE else View.GONE }
    }

    override fun hideProgress() {
        runOnUiThread { progressBar.visibility = View.GONE }
    }

    override fun showMessage(message: String) {
        runOnUiThread { Snackbar.make(mainActivityRootView, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun showMessage(resId: Int) {
        runOnUiThread { Snackbar.make(mainActivityRootView, resId, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }
}