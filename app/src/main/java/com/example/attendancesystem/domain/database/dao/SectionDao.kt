package com.example.attendancesystem.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.attendancesystem.domain.database.tables.SectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSection(sectionEntity: SectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSections(sections: List<SectionEntity>)

    @Update
    suspend fun updateSection(sectionEntity: SectionEntity)

    @Delete
    suspend fun deleteSection(sectionEntity: SectionEntity)

    // Get all sections for a specific year
    @Query("SELECT * FROM sections WHERE year = :year ORDER BY classId ASC, sectionName ASC")
    fun getSectionsForYear(year: Int): Flow<List<SectionEntity>>

    // Get all sections for a specific class in a specific year
    @Query("SELECT * FROM sections WHERE classId = :classId AND year = :year ORDER BY sectionName ASC")
    fun getSectionsByClassAndYear(classId: Int, year: Int): Flow<List<SectionEntity>>

    @Query("SELECT * FROM sections WHERE sectionId = :id LIMIT 1")
    suspend fun getSectionById(id: Int): SectionEntity?

    @Query("""
        SELECT * FROM sections 
        WHERE classId = :classId AND sectionName = :sectionName AND year = :year
        LIMIT 1
    """)
    suspend fun getSectionByClassAndName(
        classId: Int,
        sectionName: String,
        year: Int
    ): SectionEntity?
}
