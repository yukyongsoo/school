package com.yuk.school.subject.outbound

import com.yuk.school.subject.Subject
import com.yuk.school.subject.SubjectId
import com.yuk.school.subject.SubjectRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface SubjectMongoRepository : ReactiveMongoRepository<Subject, ObjectId>, SubjectRepository {
    override suspend fun new(entity: Subject): Subject? {
        return save(entity).awaitSingleOrNull()
    }

    override suspend fun get(subjectId: SubjectId): Subject? {
        return findById(subjectId.toObjectId()).awaitSingleOrNull()
    }
}
