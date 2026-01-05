package com.mysite.sbb

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Test
    fun testJpa(){
        val q1 = Question()
        q1.subject = "sbb가 무엇인가요?"
        q1.content = "sbb에 대해서 알고 싶습니다."
        this.questionRepository.save(q1)

        val q2 = Question()
        q2.subject = "스프링부트 모델 질문입니다."
        q2.content = "id는 자동으로 생성되나요?"
        this.questionRepository.save(q2)

    }

    @Test
    fun readJpa(){
        val all= this.questionRepository.findAll()
        assertEquals(2,all.size)
        val q = all[0]
        assertEquals(q.subject,"sbb가 무엇인가요?")
        println("${q.subject}: ${q.content}")
    }

    @Test
    fun contextLoads() {
    }

}
