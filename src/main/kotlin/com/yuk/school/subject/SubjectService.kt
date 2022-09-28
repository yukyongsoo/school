package com.yuk.school.subject

import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository
) {
    suspend fun save(): Subject {
        val entity = Subject("과목")

        return subjectRepository.save(entity)
            ?: throw RuntimeException()
    }

    suspend fun get(subjectId: SubjectId): Subject {
        return subjectRepository.get(subjectId)
            ?: throw RuntimeException()
    }
}
