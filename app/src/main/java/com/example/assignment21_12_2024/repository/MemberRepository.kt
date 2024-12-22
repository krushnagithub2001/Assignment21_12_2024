package com.example.assignment21_12_2024.repository

import androidx.lifecycle.LiveData
import com.example.assignment21_12_2024.RoomDatabase.MemberDao
import com.example.assignment21_12_2024.RoomDatabase.MemberEntity

class MemberRepository(private val memberDao: MemberDao) {
    val allMembers: LiveData<List<MemberEntity>> = memberDao.getAllMembers()

    suspend fun insertMember(member: MemberEntity) {
        memberDao.insertMember(member)
    }
}
