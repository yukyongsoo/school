package com.yuk.school.student

import com.yuk.school.student.outbound.StudentMongoRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Repository

@Repository
class StudentRepository(
    private val repository: StudentMongoRepository
) {
    suspend fun save(entity: Student): Student? {
        return repository.save(entity).awaitFirstOrNull()
    }

    suspend fun getOrNull(studentId: StudentId): Student? {
        return repository.findById(studentId.toObjectId()).awaitFirstOrNull()
    }
}
