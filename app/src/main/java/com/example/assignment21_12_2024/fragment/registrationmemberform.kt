package com.example.assignment21_12_2024.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignment21_12_2024.RoomDatabase.AppDatabase
import com.example.assignment21_12_2024.databinding.FragmentRegistrationmemberformBinding
import com.example.assignment21_12_2024.viewmodel.MemberViewModel
import com.example.assignment21_12_2024.RoomDatabase.MemberEntity
import com.example.assignment21_12_2024.repository.MemberRepository
import com.example.assignment21_12_2024.viewmodel.MemberViewModelFactory

class registrationmemberform : Fragment() {
    private lateinit var binding: FragmentRegistrationmemberformBinding
    private lateinit var viewModel: MemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationmemberformBinding.inflate(inflater, container, false)
        initView()
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        val memberDao = AppDatabase.getDatabase(requireActivity().application).memberDao()
        val repository = MemberRepository(memberDao)
        val factory = MemberViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MemberViewModel::class.java)
    }

    private fun initView() {
        binding.btnSunmit.setOnClickListener {
            val name = binding.edtMemberName.text.toString()
            val mobileNumber = binding.edtMobileNumber.text.toString()
            val selectedRadioButtonId = binding.rgRole.checkedRadioButtonId
            val selectedRadioButton: RadioButton = binding.root.findViewById(selectedRadioButtonId)
            val selectedGenderId = binding.rgGender.checkedRadioButtonId

             val selectedGender = when (selectedGenderId) {
                binding.rbMale.id -> "Male"
                binding.rbFemale.id -> "Female"
                else -> "other"
            }
            val selectedmartial = when (selectedGenderId) {
                binding.rbMarried.id -> "Married"
                binding.rbUnmarried.id -> "Unmarried"
                else -> ""
            }
            val role = selectedRadioButton.text.toString()
            val gender =selectedGender.toString()
            val subscriptionAmount = binding.edtSubscriptionFee.text.toString().toDoubleOrNull() ?: 0.0
            val loanAmount = binding.edtLoanAmount.text.toString().toDoubleOrNull() ?: 0.0
            val depositAmount = binding.edtDepositAmount.text.toString().toDoubleOrNull() ?: 0.0
            val dateOfBirth =binding.edtDOB.text.toString().toDoubleOrNull() ?: 0.0
            val dateOfJoining =binding.edtDOJ.text.toString().toDoubleOrNull() ?: 0.0
            val maritalStatus = selectedmartial.toString()
            val dateOfMarriage =  binding.edtMarriageDate.text.isNotEmpty()

            val region = binding.edtRegion.text.toString()
            val category = binding.edtCategory.text.toString()
            val aadharNumber = binding.edtAadhar.text.toString()
          //  Log.d("RegistrationForm", "Created MemberEntity: $name, $mobileNumber, $role, $gender, $subscriptionAmount, $loanAmount, $depositAmount, $maritalStatus")

            val memberEntity = MemberEntity(
                name = name,
                mobileNumber = mobileNumber,
                role = role,
                subscriptionAmount = subscriptionAmount,
                loanAmount = loanAmount,
                depositAmount = depositAmount,
                gender = gender,
                dateOfBirth = dateOfBirth,
                dateOfJoining = dateOfJoining,
                maritalStatus = maritalStatus,
                dateOfMarriage = dateOfMarriage,
                region = region,
                category = category,
                aadharNumber = aadharNumber
            )
          //  Log.d("RegistrationForm", "Inserting into the database: $memberEntity")

            viewModel.insert(memberEntity)
            Toast.makeText( requireContext(), "Member submitted successfully!", Toast.LENGTH_SHORT).show()

            //  Log.d("RegistrationForm", "Insertion completed")

        }
    }
}
