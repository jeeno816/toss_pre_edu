package com.mysite.sbb

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Autowired
    lateinit var answerRepository: AnswerRepository

    @Test
    fun writeJpa(){
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
    fun find_by_id(){
        val oq = questionRepository.findById(1)
        if(oq.isPresent){
            val q = oq.get()
            assertEquals("sbb가 무엇인가요?",q.subject)
        }
    }

    @Test
    fun find_by_subject(){
        val q = questionRepository.findQuestionBySubject("sbb가 무엇인가요?")
        assertEquals(q.id,1)
    }

    @Test
    fun find_by_subject_and_content(){
        val q = questionRepository.findBySubjectAndContent(
            "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다."
        )
        assertEquals(q.id,1)
    }

    @Test
    fun find_by_subject_like(){
        val ql = questionRepository.findBySubjectLike("sbb%")
        val q = ql[0]
        assertEquals("sbb가 무엇인가요?", q.subject)
    }
    @Test
    fun writeJpa_new(){

        val q3 = Question()
        q3.subject = "kotlin.. 재밌군"
        q3.content = "여러분도 재밌나요?"
        this.questionRepository.save(q3)
    }

    @Test
    fun edit(){
        val oq = questionRepository.findById(1)
        if(oq.isPresent){
            val q = oq.get()
            q.subject = "수정된 제목"
            this.questionRepository.save(q)
        }
    }
    @Test
    fun remove(){
        val oq = questionRepository.findById(1)
        if(oq.isPresent){
            val q = oq.get()
            questionRepository.delete(q)
        }
    }

    @Test
    fun add_answer(){
        val oq = questionRepository.findById(2)
        assertTrue(oq.isPresent)
        val q = oq.get()
        val answer = Answer()
        answer.content = "네 자동으로 생성됩니다."
        answer.question=q
        answerRepository.save(answer)

    }

    @Test
    fun find_answer(){
        val oa = answerRepository.findById(1)
        assertTrue(oa.isPresent)
        val q = oa.get()
        assertEquals(q.question?.id,2)
    }

    @Test
    @Transactional
    fun get_answer_by_question(){
        val oq = questionRepository.findById(2)
        assertTrue(oq.isPresent)
        val q = oq.get()
        val al = q.answerList
        assertEquals(al.size, 1)
        assertEquals(al[0].content,"네 자동으로 생성됩니다.")
    }

    @Test
    fun contextLoads() {

    }

}
