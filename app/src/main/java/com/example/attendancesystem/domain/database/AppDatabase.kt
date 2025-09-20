package com.example.attendancesystem.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.dao.StudentHistoryDao
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import com.example.attendancesystem.domain.database.tables.StudentHistoryEntity

@Database(
    entities = [
        ClassEntity::class,
        StudentEntity::class,
        AttendanceEntity::class,
        StudentHistoryEntity::class
    ],
    version = 1
)
abstract class AttendanceDatabase : RoomDatabase() {

    abstract fun classDao(): ClassDao
    abstract fun studentDao(): StudentDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun studentHistoryDao(): StudentHistoryDao

    companion object {
        const val DATABASE_NAME = "attendance_db"
    }
}
