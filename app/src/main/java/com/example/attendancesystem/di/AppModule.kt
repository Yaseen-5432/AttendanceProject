package com.example.attendancesystem.di

import android.app.Application
import com.example.attendancesystem.domain.database.AttendanceDatabase
import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.dao.StudentHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AttendanceDatabase {
        // Delegate to the singleton provider which also runs prepopulation once
        return AttendanceDatabase.getDatabase(app)
    }

   @Provides
   fun provideAttendanceDao(database: AttendanceDatabase): AttendanceDao =
       database.attendanceDao()

    @Provides
    fun provideClassDao(database: AttendanceDatabase): ClassDao =
        database.classDao()


    @Provides
    fun provideStudentDao(database: AttendanceDatabase): StudentDao =
        database.studentDao()

    @Provides
    fun provideStudentHistoryDao(database: AttendanceDatabase): StudentHistoryDao =
        database.studentHistoryDao()
}