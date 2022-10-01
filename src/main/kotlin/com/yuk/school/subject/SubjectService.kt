package com.yuk.school.subject

import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository
) {
    suspend fun save(name: String): Subject {
        val entity = Subject(name)

        return subjectRepository.save(entity)
            ?: throw RuntimeException()
    }

    suspend fun get(subjectId: SubjectId): Subject {
        return subjectRepository.get(subjectId)
            ?: throw RuntimeException()
    }
}
