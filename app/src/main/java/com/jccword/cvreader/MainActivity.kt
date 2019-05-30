package com.jccword.cvreader

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.jccword.cvreader.ui.Navigation
import com.jccword.cvreader.ui.NotificationUi
import com.jccword.cvreader.ui.ProgressUi
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : ProgressUi, NotificationUi, DaggerAppCompatActivity() {

    @Inject
    lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            navigation.toCV()
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
