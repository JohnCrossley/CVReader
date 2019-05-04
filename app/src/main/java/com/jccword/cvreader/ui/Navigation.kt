package com.jccword.cvreader.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import com.jccword.cvreader.R
import com.jccword.cvreader.what.CVFragment

class Navigation(private val fragmentManager: FragmentManager) {

    fun toCV() {
            fragmentManager.transaction {
                replace(R.id.content, CVFragment.newInstance(), CVFragment.TAG)
                addToBackStack(CVFragment.TAG)
            }
    }

}
