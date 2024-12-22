package com.example.assignment21_12_2024.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment21_12_2024.RoomDatabase.MemberEntity
import com.example.assignment21_12_2024.repository.MemberRepository
import kotlinx.coroutines.launch

class MemberViewModel(private val repository: MemberRepository) : ViewModel() {
    val allMembers: LiveData<List<MemberEntity>> = repository.allMembers

    fun insert(member: MemberEntity) = viewModelScope.launch {
        repository.insertMember(member)
    }
}
