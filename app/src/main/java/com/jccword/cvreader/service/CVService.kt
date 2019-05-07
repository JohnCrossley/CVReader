package com.jccword.cvreader.service

import com.jccword.cvreader.domain.CV
import io.reactivex.Single
import retrofit2.http.GET

interface CVService {
    companion object {
        const val URL = "https://www.switchbacksolutions.co.uk"
    }

    @GET("/cv.json")
    fun getCV() : Single<CV>

}
