package com.jccword.cvreader.repository

import com.jccword.cvreader.service.CVService

class CVRepository(var cvService: CVService) {

    fun getCV() = cvService.getCV()

}
