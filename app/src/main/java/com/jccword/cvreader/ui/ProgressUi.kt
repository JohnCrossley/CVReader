package com.jccword.cvreader.ui

/**
 * Shows UI progress in the Toolbar
 */
interface ProgressUi {

    fun showProgress(show: Boolean = true)

    fun hideProgress()

}
