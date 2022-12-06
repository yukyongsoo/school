package com.yuk.school.student.outbound

import com.yuk.school.student.Student
import com.yuk.school.student.StudentId
import com.yuk.school.student.StudentRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface StudentMongoRepository : ReactiveMongoRepository<Student, ObjectId>, StudentRepository {
    override suspend fun new(entity: Student): Student? {
        return save(entity).awaitFirstOrNull()
    }

    override suspend fun getOrNull(studentId: StudentId): Student? {
        return findById(studentId.toObjectId()).awaitFirstOrNull()
    }
}
