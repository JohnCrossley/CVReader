package com.jccword.cvreader.what

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jccword.cvreader.R
import com.jccword.cvreader.cv.CVViewModel
import com.jccword.cvreader.di.InjectableModelViewFactory
import com.jccword.cvreader.ui.Navigation
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cv.*
import kotlinx.android.synthetic.main.inc_work_row.view.*
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

        model.name.observe(this, Observer { name.text = it })
        model.label.observe(this, Observer { label.text = it })
        model.picture.observe(this, Observer { Picasso.get().load(it).into(photo) })
        model.email.observe(this, Observer { email.text = it })
        model.phone.observe(this, Observer { phone.text = it })
        model.website.observe(this, Observer { website.text = it })
        model.summary.observe(this, Observer { summary.text = it })

        model.work.observe(this, Observer { it ->
            for (j in it) {
                val job = layoutInflater.inflate(R.layout.inc_work_row, work, false)

                job.company.text = j.company
                job.position.text = j.position
                job.startDate.text = j.startDate
                job.endDate.text = j.endDate
                job.jobSummary.text = j.summary

                j.highlights?.let {
                    job.jobHighlights.text = toBulletList(it)
                }

                work.addView(job)
            }
        })
    }

    private fun toBulletList(highlights: List<String>): String {
        val sb = StringBuilder()
        for (highlight in highlights)
            sb.append(context?.getString(R.string.bullet_point) + highlight + context?.getString(R.string.newline))

        return sb.toString()
    }

    companion object {
        val TAG = "CVFragment"

        @JvmStatic
        fun newInstance() = CVFragment()
    }

}