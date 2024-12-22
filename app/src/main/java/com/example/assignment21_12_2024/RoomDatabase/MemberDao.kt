package com.example.assignment21_12_2024.RoomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData

@Dao
interface MemberDao {
    @Insert
    suspend fun insertMember(member: MemberEntity)

    @Query("SELECT * FROM members")
    fun getAllMembers(): LiveData<List<MemberEntity>>
}
