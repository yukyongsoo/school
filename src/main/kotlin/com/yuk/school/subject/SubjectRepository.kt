package com.yuk.school.subject

import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository {
    suspend fun new(entity: Subject): Subject?
    suspend fun get(subjectId: SubjectId): Subject?
}
