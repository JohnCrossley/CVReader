package com.jccword.cvreader.domain

class CV {
    val basics: Basics = Basics()
    val work: List<Work> = mutableListOf()
//    val skills: List<Skill> = mutableListOf()
//    val interests: List<String> = mutableListOf()
}

class Basics {
    val name: String? = null
    val label: String? = null
    val picture: String? = null

    val email: String? = null
    val phone: String? = null
    val website: String? = null
    val summary: String? = null
}

class Work {
    val company: String? = null
    val position: String? = null
    val website: String? = null
    val startDate: String? = null
    val endDate: String? = null
    val summary: String? = null
    val highlights: List<String>? = null
}

class Skill {
    val name: String? = null
    val level: String? = null
}
