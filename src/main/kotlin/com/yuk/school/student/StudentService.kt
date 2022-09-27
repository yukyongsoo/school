package com.yuk.school.student

import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository
) {
    suspend fun save(): Student {
        val entity = Student(
            "학생"
        )

        return studentRepository.save(entity)
            ?: throw RuntimeException()
    }

    suspend fun get(studentId: StudentId): Student {
        return studentRepository.getOrNull(studentId)
            ?: throw RuntimeException()
    }
}
