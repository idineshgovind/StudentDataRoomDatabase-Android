package com.example.a7_simpleroomapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "zsid") val zsid:String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "gender") val gender:String?,
    @ColumnInfo(name = "school") val school : String?
)
