package com.example.attendancesystem.domain.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.attendancesystem.domain.database.dao.AttendanceDao
import com.example.attendancesystem.domain.database.dao.ClassDao
import com.example.attendancesystem.domain.database.dao.StudentDao
import com.example.attendancesystem.domain.database.dao.StudentHistoryDao
import com.example.attendancesystem.domain.database.tables.AttendanceEntity
import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.domain.database.tables.StudentEntity
import com.example.attendancesystem.domain.database.tables.StudentHistoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ClassEntity::class,
        StudentEntity::class,
        AttendanceEntity::class,
        StudentHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AttendanceDatabase : RoomDatabase() {

    abstract fun classDao(): ClassDao
    abstract fun studentDao(): StudentDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun studentHistoryDao(): StudentHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AttendanceDatabase? = null

        const val DATABASE_NAME = "attendance_db"
        private const val PREFS_NAME = "db_prefs"
        private const val KEY_PREPOPULATED = "prepopulated"

        // Singleton database instance + reliable one-time prepopulation
        fun getDatabase(context: Context): AttendanceDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceDatabase::class.java,
                    DATABASE_NAME
                )
                    // .fallbackToDestructiveMigration() // enable if you bump schema without migration
                    .build()

                INSTANCE = db

                // Prepopulate once on a background thread after instance is set
                CoroutineScope(Dispatchers.IO).launch {
                    runCatching {
                        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        if (!prefs.getBoolean(KEY_PREPOPULATED, false)) {
                            prePopulateDatabase(context, db)
                            prefs.edit().putBoolean(KEY_PREPOPULATED, true).apply()
                        } else {
                            Log.d("AttendanceDatabase", "Prepopulation skipped (already done)")
                        }
                    }.onFailure { e ->
                        Log.e("AttendanceDatabase", "Prepopulation failed", e)
                    }
                }

                db
            }
        }

        // Read JSON assets and insert into DB in the correct order inside a transaction
        private suspend fun prePopulateDatabase(context: Context, db: AttendanceDatabase) {
            val start = System.currentTimeMillis()
            // Parse JSON into entity lists
            val gson = Gson()

            val classesJson = context.assets.open("classes.json").bufferedReader().use { it.readText() }
            val classesType = object : TypeToken<List<ClassEntity>>() {}.type
            val classes: List<ClassEntity> = gson.fromJson(classesJson, classesType) ?: emptyList()

            val studentsJson = context.assets.open("student.json").bufferedReader().use { it.readText() }
            val studentsType = object : TypeToken<List<StudentEntity>>() {}.type
            val students: List<StudentEntity> = gson.fromJson(studentsJson, studentsType) ?: emptyList()

            val historyJson = context.assets.open("studenthistory.json").bufferedReader().use { it.readText() }
            val historyType = object : TypeToken<List<StudentHistoryEntity>>() {}.type
            val histories: List<StudentHistoryEntity> = gson.fromJson(historyJson, historyType) ?: emptyList()

            val attendanceJson = context.assets.open("attendance.json").bufferedReader().use { it.readText() }
            val attendanceType = object : TypeToken<List<AttendanceEntity>>() {}.type
            val attendance: List<AttendanceEntity> = gson.fromJson(attendanceJson, attendanceType) ?: emptyList()

            // Insert in order: classes -> students -> histories -> attendance
            db.withTransaction {
                if (classes.isNotEmpty()) db.classDao().insertClasses(classes)
                if (students.isNotEmpty()) db.studentDao().insertStudents(students)
                if (histories.isNotEmpty()) db.studentHistoryDao().insertHistoryList(histories)
                if (attendance.isNotEmpty()) db.attendanceDao().insertAttendanceList(attendance)
            }

            // Query actual table counts to verify
            val classCount = db.classDao().count()
            val studentCount = db.studentDao().count()
            val historyCount = db.studentHistoryDao().count()
            val attendanceCount = db.attendanceDao().count()

            Log.d(
                "AttendanceDatabase",
                "Pre-populated rows (actual): classes=" + classCount +
                        ", students=" + studentCount +
                        ", histories=" + historyCount +
                        ", attendance=" + attendanceCount +
                        " | From assets sizes: classes=" + classes.size +
                        ", students=" + students.size +
                        ", histories=" + histories.size +
                        ", attendance=" + attendance.size +
                        " | Took " + (System.currentTimeMillis() - start) + "ms"
            )
        }
    }
}
