package com.example.attendancesystem.domain.model

data class ClassWithSection(
    val className: String,
    val sectionName: String? // null if no section
) {
    fun displayName(): String {
        return if (sectionName != null) "$className$sectionName" else className
    }
}