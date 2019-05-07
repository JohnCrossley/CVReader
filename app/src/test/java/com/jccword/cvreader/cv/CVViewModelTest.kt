package com.jccword.cvreader.cv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jccword.cvreader.R
import com.jccword.cvreader.domain.CV
import com.jccword.cvreader.service.CVService
import com.jccword.cvreader.ui.NotificationUi
import com.jccword.cvreader.ui.ProgressUi
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CVViewModelTest {

    private lateinit var sut: CVViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockCVService: CVService

    @Mock
    private lateinit var mockProgressUi: ProgressUi

    @Mock
    private lateinit var mockNotificationUi: NotificationUi

    var cv = CV()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun showsProgressWhenFetchingCv() {
        // init
        `when`(mockCVService.getCV()).thenReturn(Single.just(cv))

        // run
        sut = CVViewModel(mockCVService, mockProgressUi, mockNotificationUi)

        // verify
        verify(mockProgressUi).showProgress()
    }

    @Test
    fun hidesProgressWhenFetchCompleteForCv() {
        // init
        `when`(mockCVService.getCV()).thenReturn(Single.just(cv))

        // run
        sut = CVViewModel(mockCVService, mockProgressUi, mockNotificationUi)

        //verify
        verify(mockProgressUi).hideProgress()
    }

    @Test
    fun showsErrorWhenServiceFails() {
        // init
        `when`(mockCVService.getCV()).thenReturn(Single.error(RuntimeException("oh no!")))

        // run
        sut = CVViewModel(mockCVService, mockProgressUi, mockNotificationUi)

        // verify
        verify(mockNotificationUi).run { showMessage(R.string.network_error) }
    }

    @Test
    fun hidesProgressWhenServiceFails() {
        // init
        `when`(mockCVService.getCV()).thenReturn(Single.error(RuntimeException("oh no!")))

        // run
        sut = CVViewModel(mockCVService, mockProgressUi, mockNotificationUi)

        // verify
        verify(mockProgressUi).run { hideProgress() }
    }

}