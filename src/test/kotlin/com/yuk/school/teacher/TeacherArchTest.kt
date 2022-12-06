package com.yuk.school.teacher

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures.onionArchitecture
import org.junit.jupiter.api.Test

class TeacherArchTest {
    private val serviceAndDomain = "com.yuk.school.teacher.."
    private val outbound = "com.yuk.school.teacher.outbound.."
    private val inbound = "com.yuk.school.teacher.inbound.."

    @Test
    fun `layer 테스트`() {
        val classes = ClassFileImporter().importPackages(serviceAndDomain)

        onionArchitecture()
            .ensureAllClassesAreContainedInArchitecture()
            .applicationServices(serviceAndDomain)
            .domainServices(serviceAndDomain)
            .domainModels(serviceAndDomain)
            .adapter("outbound", outbound)
            .adapter("inbound", inbound)
            .check(classes)
    }
}
