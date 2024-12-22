package com.example.assignment21_12_2024.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val mobileNumber: String,
    val role: String,
    val subscriptionAmount: Double,
    val loanAmount: Double,
    val depositAmount: Double,
    val gender: String,
    val dateOfBirth: Double,
    val dateOfJoining: Double,
    val maritalStatus: String,
    val dateOfMarriage: Boolean,
    val region: String,
    val category: String,
    val aadharNumber: String
)
