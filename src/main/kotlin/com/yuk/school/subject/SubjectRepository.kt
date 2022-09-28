package com.yuk.school.subject

import com.yuk.school.subject.outbound.SubjectMongoRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Repository

@Repository
class SubjectRepository(
    private val subjectMongoRepository: SubjectMongoRepository
) {
    suspend fun save(entity: Subject): Subject? {
        return subjectMongoRepository.save(entity).awaitSingleOrNull()
    }

    suspend fun get(subjectId: SubjectId): Subject? {
        return subjectMongoRepository.findById(subjectId.toObjectId()).awaitSingleOrNull()
    }
}
