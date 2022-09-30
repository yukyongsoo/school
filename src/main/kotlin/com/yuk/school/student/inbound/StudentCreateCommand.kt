package com.yuk.school.student.inbound

data class StudentCreateCommand(
    val name: String,
    val classId: String
)
