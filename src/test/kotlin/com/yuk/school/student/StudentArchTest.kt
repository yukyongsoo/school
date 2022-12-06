package com.yuk.school.student

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures.onionArchitecture
import org.junit.jupiter.api.Test

class StudentArchTest {
    private val serviceAndDomain = "com.yuk.school.student.."
    private val outbound = "com.yuk.school.student.outbound.."
    private val inbound = "com.yuk.school.student.inbound.."

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
