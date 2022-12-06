package com.yuk.school.student

import org.springframework.stereotype.Repository

@Repository
interface StudentRepository {
    suspend fun new(entity: Student): Student?
    suspend fun getOrNull(studentId: StudentId): Student?
}
