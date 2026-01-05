package com.mysite.sbb

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(length = 200)
    var subject: String = ""

    @Column(columnDefinition = "TEXT")
    var content: String = ""

    var createDate : LocalDateTime = LocalDateTime.now()

    @OneToMany(mappedBy = "question", cascade = [CascadeType.REMOVE])
    var answerList :MutableList<Answer> = mutableListOf()


}