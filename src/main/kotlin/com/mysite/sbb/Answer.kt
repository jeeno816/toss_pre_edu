package com.mysite.sbb

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(columnDefinition = "TEXT")
    var content: String = ""

    var createDate: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    var question:Question? = null
}