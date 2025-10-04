package com.example.attendancesystem.domain.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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

        fun getDatabase(context: Context): AttendanceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // ✅ Use INSTANCE here instead of local 'instance'
                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.let { database ->
                                    prePopulateDatabase(context, database)
                                }
                            }
                        }
                    })
                    .build()

                INSTANCE = instance
                instance
            }
        }


        // ✅ Prepopulate database from assets/classes.json
        private suspend fun prePopulateDatabase(context: Context, db: AttendanceDatabase) {
            try {
                val inputStream = context.assets.open("classes.json")
                val json = inputStream.bufferedReader().use { it.readText() }

                val type = object : TypeToken<List<ClassEntity>>() {}.type
                val classList: List<ClassEntity> = Gson().fromJson(json, type)

                println("DEBUG: Classes loaded from JSON = ${classList.size}") // 🔍 Debug

                db.classDao().insertClasses(classList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
