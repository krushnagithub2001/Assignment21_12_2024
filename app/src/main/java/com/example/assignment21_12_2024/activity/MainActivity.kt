package com.example.assignment21_12_2024.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment21_12_2024.R
import com.example.assignment21_12_2024.RoomDatabase.AppDatabase
import com.example.assignment21_12_2024.adapter.MemberAdapter
import com.example.assignment21_12_2024.databinding.ActivityMainBinding
import com.example.assignment21_12_2024.dataclasses.Member
import com.example.assignment21_12_2024.fragment.registrationmemberform
import com.example.assignment21_12_2024.repository.MemberRepository
import com.example.assignment21_12_2024.viewmodel.MemberViewModel
import com.example.assignment21_12_2024.viewmodel.MemberViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MemberAdapter

    private val memberViewModel: MemberViewModel by viewModels {
        val memberDao = AppDatabase.getDatabase(applicationContext).memberDao()
        val repository = MemberRepository(memberDao)
        MemberViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setUpListeners()

        memberViewModel.allMembers.observe(this, Observer { members ->
            adapter = MemberAdapter(members)
            binding.recyclerViewMembers.adapter = adapter
        })
    }

    private fun initView() {
        binding.recyclerViewMembers.layoutManager = LinearLayoutManager(this)
    }


    private fun setUpListeners() {
        binding.btnAddMember.setOnClickListener {
            navigateToAddMemberFragment()
        }
    }


    private fun navigateToAddMemberFragment() {
        binding.btnAddMember.visibility = View.GONE
        val newFragment = registrationmemberform()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(
            android.R.animator.fade_in,
            android.R.animator.fade_out
        )

        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
