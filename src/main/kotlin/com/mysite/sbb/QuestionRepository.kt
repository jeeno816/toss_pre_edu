package com.mysite.sbb

import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {
    fun findQuestionBySubject(subject: String): Question
    fun findBySubjectAndContent(subject: String, content: String): Question
    fun findBySubjectLike(subject: String): List<Question>
}