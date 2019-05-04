package com.jccword.cvreader.what

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import com.jccword.cvreader.ui.Navigation
import com.jccword.cvreader.R
import com.jccword.cvreader.cv.CVViewModel
import com.jccword.cvreader.di.InjectableModelViewFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CVFragment : DaggerFragment() {

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var injectableModelViewFactory: InjectableModelViewFactory

    private lateinit var model: CVViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        model = ViewModelProviders.of(this, injectableModelViewFactory).get(CVViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return true
    }

    companion object {
        val TAG = "CVFragment"

        @JvmStatic
        fun newInstance() = CVFragment()
    }

}