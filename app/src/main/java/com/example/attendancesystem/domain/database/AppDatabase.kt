package com.example.attendancesystem.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.SectionDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.SectionEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity

@Database(
    entities = [
        ClassEntity::class,
        SectionEntity::class,
        StudentEntity::class,
        AttendanceEntity::class
    ],
    version = 1
)
abstract class AttendanceDatabase : RoomDatabase() {

    abstract fun classDao(): ClassDao
    abstract fun sectionDao(): SectionDao
    abstract fun studentDao(): StudentDao
    abstract fun attendanceDao(): AttendanceDao

    companion object {
        const val DATABASE_NAME = "attendance_db"
    }
}
