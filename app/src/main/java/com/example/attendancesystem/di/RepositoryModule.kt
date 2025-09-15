package com.example.attendancesystem.di

import com.example.attendancesystem.data.AttendanceRepositoryImpl
import com.example.attendancesystem.data.SchoolRepositoryImpl
import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.SectionDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.repo.AttendanceRepository
import com.example.attendancesystem.domain.database.repo.SchoolRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAttendanceRepository(
        attendanceDao: AttendanceDao
    ): AttendanceRepository {
        return AttendanceRepositoryImpl(attendanceDao)
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(classDao: ClassDao, sectionDao: SectionDao, studentDao: StudentDao): SchoolRepository {
        return SchoolRepositoryImpl(classDao, sectionDao, studentDao)
    }


}