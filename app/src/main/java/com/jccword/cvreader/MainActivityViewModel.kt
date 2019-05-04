package com.jccword.cvreader

import androidx.lifecycle.ViewModel
import com.jccword.cvreader.ui.Navigation

class MainActivityViewModel(val navigation: Navigation): ViewModel() {
    init {
        navigation.toCV()
    }
}
