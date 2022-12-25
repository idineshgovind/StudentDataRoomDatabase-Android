package com.example.a7_simpleroomapp


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface StudentDao {

    @Query("SELECT * FROM student_table")
    fun getALl(): List<Student>

    @Query("SELECT * FROM student_table WHERE zsid LIKE :zsid LIMIT 1")
    fun findByZsid(zsid: String):Student

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("DELETE FROM student_table")
    fun deleteAll()
}