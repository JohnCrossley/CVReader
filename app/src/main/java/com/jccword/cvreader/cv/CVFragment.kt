package com.jccword.cvreader.what

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jccword.cvreader.R
import com.jccword.cvreader.cv.CVViewModel
import com.jccword.cvreader.cv.State
import com.jccword.cvreader.di.InjectableModelViewFactory
import com.jccword.cvreader.ui.Navigation
import com.jccword.cvreader.ui.NotificationUi
import com.jccword.cvreader.ui.ProgressUi
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cv.*
import kotlinx.android.synthetic.main.inc_work_row.view.*
import javax.inject.Inject

class CVFragment : DaggerFragment() {

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var progressUi: ProgressUi

    @Inject
    lateinit var notificationUi: NotificationUi

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

        model.state.observe(this, Observer {
            when(it) {
                State.LOADING -> progressUi.showProgress()
                State.READY -> progressUi.hideProgress()
                State.ERROR -> {
                    progressUi.hideProgress()
                    notificationUi.showMessage(R.string.network_error)
                }
            }
        })

        model.name.observe(this, Observer { name.text = it })
        model.label.observe(this, Observer { label.text = it })
        model.picture.observe(this, Observer { Picasso.get().load(it).into(photo) })
        model.email.observe(this, Observer { email.text = it })
        model.phone.observe(this, Observer { phone.text = it })
        model.website.observe(this, Observer { website.text = it })
        model.summary.observe(this, Observer { summary.text = it })

        model.work.observe(this, Observer { it ->
            for (placement in it) {
                val job = layoutInflater.inflate(R.layout.inc_work_row, work, false)

                job.company.text = placement.company
                job.position.text = placement.position
                job.startDate.text = placement.startDate
                job.endDate.text = placement.endDate
                job.jobSummary.text = placement.summary

                placement.highlights?.let {
                    job.jobHighlights.text = toBulletList(it)
                }

                work.addView(job)
            }
        })

        model.skills.observe(this, Observer { skills.text = it })
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